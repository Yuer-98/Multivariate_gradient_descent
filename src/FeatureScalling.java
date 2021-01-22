import java.util.Arrays;
import java.util.List;

/**先把特征缩放单独写出来
 * 因为GD类中我使用的是List存储数据
 * 这样不便于竖着按特征去遍历数据（只能横着按一个一个样本去遍历）
 * 用二维数组存储数据就没这个烦恼了
 * 这里就是对二维数组存储的特征进行缩放*/
class FeatureScalling {
    private double[] ranges;
    private double[] avgs;

    /**特征缩放*/
    public void featureScaling(double[][] datas){
        ranges = new double[datas[0].length-1];
        avgs = new double[datas[0].length-1];
        //注意最后一列是y
        for(int j=0;j<datas[0].length-1;j++){
            double min = datas[0][j];
            double max = datas[0][j];
            double sum = 0.0;
            double avg = 0.0;
            for(int i=0;i<datas.length;i++){
                if(datas[i][j]>max){
                    max = datas[i][j];
                }
                if(datas[i][j]<min){
                    min = datas[i][j];
                }
                sum += datas[i][j];
            }
            avg = sum / datas.length;
            ranges[j] = max - min;
            avgs[j] = avg;
            for(int i=0;i<datas.length;i++){
                datas[i][j] = (datas[i][j] - avg) / (max - min);
            }
        }
    }

    public void scallingData(double[] data){
        for(int i=0;i<data.length;i++){
            data[i] = (data[i] - avgs[i]) / ranges[i];
        }
    }

    public void printScalling(){
        System.out.println("RANGES" + Arrays.toString(ranges));
        System.out.println("AVGs" + Arrays.toString(avgs));
    }
}
