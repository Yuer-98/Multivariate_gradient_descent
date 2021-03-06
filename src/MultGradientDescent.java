import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.text.DecimalFormat;

/**多元线性回归的梯度下降
 * 两种方式停止迭代
 * 1.最大迭代次数
 * 2.通过前后两次迭代代价函数的差值判断收敛*/
class MultGradientDescent {
    private Logger gdLogger = Logger.getLogger("gdLogger");
    private List<List<Double>> xLists;
    private List<Double> yList;
    private int pNum = 0;
    private int m = 0;
    private boolean inited = false;
    private double[] parameters;
    private int maxTimes = 10000;

    /**初始化数据函数，会自动加上 x0=1*/
    public boolean initData(List<List<Double>> xLists, List<Double> yList){
        try{
            if(xLists.size()!=yList.size()||yList.size()==0||xLists.get(0).size()==0){
                throw new Exception("数据个数不对");
            }
            this.xLists = xLists;
            for(List<Double> list : this.xLists){
                list.add(0,1.0);
            }
            this.yList = yList;
            this.pNum = this.xLists.get(0).size();
            this.m = this.yList.size();
            this.inited = true;
            return true;
        }
        catch(Exception e){
            gdLogger.warning("数据格式有误");
            return false;
        }
    }

    /**初始化数据函数，会自动加上 x0=1*/
    public boolean initData(double[][] datas){
        try{
            if(datas.length==0||datas[0].length<2){
                throw new Exception("数据个数不对");
            }
            xLists = new ArrayList<>();
            yList = new ArrayList<>();
            for(int i=0;i<datas.length;i++){
                List<Double> tmp = new ArrayList<>();
                yList.add(datas[i][datas[i].length-1]);
                tmp.add(1.0);
                for(int j=0;j<datas[i].length-1;j++){
                    tmp.add(datas[i][j]);
                }
                this.xLists.add(tmp);
            }
            this.pNum = this.xLists.get(0).size();
            this.m = this.yList.size();
            this.inited = true;
            return true;
        }
        catch(Exception e){
            gdLogger.warning("数据格式有误");
            return false;
        }
    }

    public boolean fit(double learningRate){
        if(!this.inited){
            gdLogger.warning("还未导入数据");
            return false;
        }
        parameters = new double[pNum];
        double preCost = getCost() + 1;
        double[] tmpP = new double[pNum];
        int times = 0;
        while(Math.abs(getCost()-preCost)>0.0000000001&&times<maxTimes){
            preCost = getCost();
            for(int i=0;i<pNum;i++){
                double term = 0;
                for(int j=0;j<m;j++){
                    double h = getPrediction(xLists.get(j).subList(1,xLists.get(i).size()));
                    term += (h - yList.get(j)) * xLists.get(j).get(i);
                }
                term *= learningRate / m;
                tmpP[i] = parameters[i] - term;
            }
            for(int i=0;i<pNum;i++){
                parameters[i] = tmpP[i];
            }
            times++;
        }
        return true;
    }

    public boolean fit(double learningRate, double maxTimes){
        if(!this.inited){
            gdLogger.warning("还未导入数据");
            return false;
        }
        parameters = new double[pNum];
        double preCost = getCost() + 1;
        double[] tmpP = new double[pNum];
        int times = 0;
        while(Math.abs(getCost()-preCost)>0.0000000001&&times<maxTimes){
            preCost = getCost();
            for(int i=0;i<pNum;i++){
                double term = 0;
                for(int j=0;j<m;j++){
                    double h = getPrediction(xLists.get(j).subList(1,xLists.get(i).size()));
                    term += (h - yList.get(j)) * xLists.get(j).get(i);
                }
                term *= learningRate / m;
                tmpP[i] = parameters[i] - term;
            }
            for(int i=0;i<pNum;i++){
                parameters[i] = tmpP[i];
            }
            times++;
        }
        return true;
    }

    public boolean fit(double learningRate, double maxTimes, double costDifference){
        if(!this.inited){
            gdLogger.warning("还未导入数据");
            return false;
        }
        parameters = new double[pNum];
        double preCost = getCost() + costDifference + 1;
        double[] tmpP = new double[pNum];
        int times = 0;
        while(Math.abs(getCost()-preCost)>costDifference&&times<maxTimes){
            preCost = getCost();
            for(int i=0;i<pNum;i++){
                double term = 0;
                for(int j=0;j<m;j++){
                    double h = getPrediction(xLists.get(j).subList(1,xLists.get(i).size()));
                    term += (h - yList.get(j)) * xLists.get(j).get(i);
                }
                term *= learningRate / m;
                tmpP[i] = parameters[i] - term;
            }
            for(int i=0;i<pNum;i++){
                parameters[i] = tmpP[i];
            }
            times++;
        }
        System.out.println("times" + times);
        return true;
    }

    public double getCost(){
        double ans = 0;
        for(int i=0;i<m;i++){
            double h = getPrediction(xLists.get(i).subList(1,xLists.get(i).size()));
            ans += Math.pow(h - yList.get(i), 2);
        }
        return ans / (2*m);
    }

    public double getPrediction(List<Double> xList){
        if(xList.size()!=pNum-1){
            gdLogger.warning("数据个数不对");
            return 0.0;
        }
        double ans = parameters[0];
        for(int i=1;i<this.pNum;i++){
            ans += parameters[i]*xList.get(i-1);
        }
        return ans;
    }

    public double getPrediction(double[] xList){
        if(xList.length!=pNum-1){
            gdLogger.warning("数据个数不对");
            return 0.0;
        }
        double ans = parameters[0];
        for(int i=1;i<this.pNum;i++){
            ans += parameters[i]*xList[i-1];
        }
        return ans;
    }

    public double[] getModel(){
        return this.parameters;
    }

    public void printDatas(){
        System.out.println("yList: " + yList);
        System.out.println("xList: " + xLists);
    }

    public void printModel(){
        StringBuilder expression = new StringBuilder();
        DecimalFormat df = new DecimalFormat("######0.000");
        expression.append(df.format(parameters[0])+" + ");
        for(int i=1;i<parameters.length;i++){
            expression.append(df.format(parameters[i])+"·x_"+i+" + ");
        }
        expression.delete(expression.length()-3,expression.length());
        System.out.println("表达式为：" + expression);
    }
}