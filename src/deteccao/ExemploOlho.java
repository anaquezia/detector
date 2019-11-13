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
public class ExemploOlho {

    public static void main(String arg[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat imagemColorida = imread("src\\pessoas\\pp.jpg", CV_LOAD_IMAGE_COLOR);

        //converter imagem em escala de cinza
        Mat imagemCinza = new Mat();

        Imgproc.cvtColor(imagemColorida, imagemCinza, COLOR_BGR2GRAY);
        CascadeClassifier classificadorFace
                = new CascadeClassifier("src\\cascades\\haarcascade_frontalface_default.xml");
        MatOfRect facesDetectadas = new MatOfRect();
        classificadorFace.detectMultiScale(imagemCinza, facesDetectadas);

        System.out.println("Total de faces detectadas: " + facesDetectadas.toArray().length);

        //posição e tamanho 
        for (Rect rect : facesDetectadas.toArray()) {
            System.out.println(rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
            Imgproc.rectangle(imagemColorida, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(224, 71, 228), 2);
        }
        MatOfRect olhosDetectados = new MatOfRect();
        CascadeClassifier classificadorOlho = new CascadeClassifier("src\\cascades\\haarcascade_eye.xml");
        classificadorOlho.detectMultiScale(imagemCinza, olhosDetectados);
        
        for(Rect rect: olhosDetectados.toArray()){
            System.out.println(rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
            Imgproc.rectangle(imagemColorida, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(13, 249, 255), 2);
        }
        Utilitarios ut = new Utilitarios();
        ut.mostraImagem(ut.convertMatToImage(imagemColorida));
    }
}
