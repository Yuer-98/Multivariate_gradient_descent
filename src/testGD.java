public class testGD {
    public static void main(String[] args){
        MultGradientDescent multGradientDescent = new MultGradientDescent();
        double[] data1 = new double[]{1.0,1.0,1.0};
        double[] data2 = new double[]{3.0,3.0,3.0};
        double[] data3 = new double[]{5.0,5.0,5.0};
        double[] data4 = new double[]{7.0,7.0};
        double[][] datas = new double[3][];
        datas[0] = data1;
        datas[1] = data2;
        datas[2] = data3;

        multGradientDescent.initData(datas);
        multGradientDescent.fit(0.001,100000, 0.0000000001);
        double prediction = multGradientDescent.getPrediction(data4);
        System.out.println(prediction);
        //multGradientDescent.printDatas();
        multGradientDescent.printModel();
    }
}
