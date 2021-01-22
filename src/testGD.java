import java.util.Arrays;

public class testGD {
    public static void main(String[] args){
        MultGradientDescent multGradientDescent = new MultGradientDescent();
        double[] data1 = new double[]{1.0,10.0,11.0};
        double[] data2 = new double[]{3.0,20.0,23.0};
        double[] data3 = new double[]{5.0,50.0,55.0};
        double[] data4 = new double[]{5.0,20.0,25.0};
        double[] data = new double[]{2.0,20.0};
        double[][] datas = new double[4][];
        datas[0] = data1;
        datas[1] = data2;
        datas[2] = data3;
        datas[3] = data4;

        multGradientDescent.initData(datas);
        multGradientDescent.printDatas();
        multGradientDescent.fit(0.001,100000, 0.00000001);
        double prediction = multGradientDescent.getPrediction(data);
        System.out.println(prediction);
        multGradientDescent.printModel();

        System.out.println("_______________________________________");

        FeatureScalling featureScalling = new FeatureScalling();
        //对样本数据特征缩放
        featureScalling.featureScaling(datas);
        multGradientDescent.initData(datas);
        multGradientDescent.printDatas();
        multGradientDescent.fit(0.001,100000, 0.00000001);
        featureScalling.printScalling();
        //对待预测数据特征缩放
        featureScalling.scallingData(data);
        System.out.println(Arrays.toString(data));
        prediction = multGradientDescent.getPrediction(data);
        System.out.println(prediction);
        multGradientDescent.printModel();

    }
}
