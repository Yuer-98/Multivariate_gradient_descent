public class testGD {
    public static void main(String[] args){
        MultGradientDescent multGradientDescent = new MultGradientDescent();
        double[] data1 = new double[]{1.0,1.01,1.01};
        double[] data2 = new double[]{3.0,3.01,3.01};
        double[] data3 = new double[]{5.0,5.01,5.01};
        double[] data4 = new double[]{7.0,7.0};
        double[][] datas = new double[3][];
        datas[0] = data1;
        datas[1] = data2;
        datas[2] = data3;

        multGradientDescent.initData(datas);
        multGradientDescent.fit(0.001,122800);
        double prediction = multGradientDescent.getPrediction(data4);
        System.out.println(prediction);
        multGradientDescent.printDatas();
    }
}
