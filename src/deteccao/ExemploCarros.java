/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deteccao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author ANA QUEZIA
 */
public class ExemploCarros {
    public static void main(String arg[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat imagemColorida = imread("src\\outros\\carro3.jpg", CV_LOAD_IMAGE_COLOR);

        //converter imagem em escala de cinza
        Mat imagemCinza = new Mat();

        Imgproc.cvtColor(imagemColorida, imagemCinza, COLOR_BGR2GRAY);
        CascadeClassifier classificadorCarro
                = new CascadeClassifier("src\\cascades\\cars.xml");
        MatOfRect carrosDetectadas = new MatOfRect();
        classificadorCarro.detectMultiScale(imagemCinza, carrosDetectadas,
       1.01 ,4 , 0, new Size(20,20), new Size(700,700));

        System.out.println("Total de carros detectadas: " + carrosDetectadas.toArray().length);

        //posição e tamanho 
        for (Rect rect : carrosDetectadas.toArray()) {
            System.out.println(rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
            if(rect.width>50){
                System.out.println("Fora de padrão");
            }
            Imgproc.rectangle(imagemColorida, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(224, 71, 228), 2);
        }
        
        Utilitarios ut = new Utilitarios();
        ut.mostraImagem(ut.convertMatToImage(imagemColorida));
    }
}
