package lightingfight;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class Unit {
    private static Image background01 =new Image(Unit.class.getResourceAsStream("/config/picture/background01.png"));
    private static Image selfPlane= new Image(Unit.class.getResourceAsStream("/config/picture/plane.gif"));
    private static Image bullet1 =new Image(Unit.class.getResourceAsStream("/config/picture/bullet01.png"));
    private static Image upgrade1 = new Image(Unit.class.getResourceAsStream("/config/picture/packH.png"));
    private static Image enemy01 = new Image(Unit.class.getResourceAsStream("/config/picture/enemy01.gif"));
    private static Image[] dead01 = getImagearray("/config/picture/dead01/long02-siwang_");
    private static Image self_bullet_blast =new Image(Unit.class.getResourceAsStream("/config/picture/bullet_blast.png"));
    private static Image e_bullet01 = new Image(Unit.class.getResourceAsStream("/config/picture/e_bullet01.png"));
    private static Image emeny02 = new Image(Unit.class.getResourceAsStream("/config/picture/enemy02.gif"));
    private static Image[] dead02 = getImagearray("/config/picture/dead02/long-03-siwang_");
    private static Image BossBullet01 =new Image(Unit.class.getResourceAsStream("/config/picture/bossBullet01.png"));
    private static Image Boss01 =new Image(Unit.class.getResourceAsStream("/config/picture/boss01.gif"));
    private static Image healing =new Image(Unit.class.getResourceAsStream("/config/picture/healing.png"));
    private static Image hp =new Image(Unit.class.getResourceAsStream("/config/picture/hp.png"));
    private static Image startb =new Image(Unit.class.getResourceAsStream("/config/picture/startbackground.png"));
    private static Image toleft =new Image(Unit.class.getResourceAsStream("/config/picture/toleft.gif"));
    private static Image toright =new Image(Unit.class.getResourceAsStream("/config/picture/toright.gif"));
    private static Image fire01 =new Image(Unit.class.getResourceAsStream("/config/picture/fire01.gif"));
    private static Image fire02 =new Image(Unit.class.getResourceAsStream("/config/picture/fire02.gif"));
    private static Image[] dead03 =getImagearray("/config/picture/dead03/armatureName_siwang_");
    private static Image b_bullet02 = new Image(Unit.class.getResourceAsStream("/config/picture/bossBullet01.png"));
    private static Image pause = new Image(Unit.class.getResourceAsStream("/config/picture/pause.png"));
    private static Image conTinue = new Image(Unit.class.getResourceAsStream("/config/picture/continue.png"));
    private static Image background02 = new Image(Unit.class.getResourceAsStream("/config/picture/background02.png"));
    private static Image background03 = new Image(Unit.class.getResourceAsStream("/config/picture/gameover.png"));
    private static Image restart = new Image(Unit.class.getResourceAsStream("/config/picture/restart.png"));
    private static Image del = new Image(Unit.class.getResourceAsStream("/config/picture/del.png"));
    private static Media selfshootingSound = new Media(Unit.class.getResource("/config/sound/selfshooting.wav").toExternalForm());
    
    static Image getbackground01()
    {
     return background01 ;
    }

    static Image getbackground02()
    {
     return background02 ;
    }
    
    static Image getbackground03()
    {
     return background03 ;
    }
    
    static Image getPlane()
    {
     return selfPlane;
    }
    
    static Image gettoleft()
    {
     return toleft;
    }

    static Image gettoright()
    {
     return toright;
    }
    
    static Image getbullet1()
    {
        return bullet1;
    }
    
    
    static Image getupgrade1()
    {
     return upgrade1;
    }
    
    
    static Image getenemy01()
    {
     return enemy01;
    }

    static Image[] getdead01()
    {
     return dead01;
    }

    static Image getself_bullet_blast()
    {
     return self_bullet_blast;
    }
    

    static Image gete_bullet01()
    {
     return e_bullet01;
    }
    
    static Image getemeny02()
    {
     return emeny02;
    }
    
    static Image[] getdead02()
    {
     return dead02;
    }
    

    static Image getB_Bullet01()
    {
     return BossBullet01;
    }

     static Image getB_Bullet02()
     {
        return b_bullet02;
     }

    static Image getBoss1(){
        return Boss01;}


    static Image gethealing(){
        return healing;}


    static Image gethp(){
        return hp;}    


    static Image getstratb(){
            return startb;} 
            
            
    static Media getshootingsound()
    {
        return selfshootingSound;
    }

    static Image getFire01()
    {
       return fire01;
    }
    
    static Image getFire02()
    {
       return fire02;
    }

    static Image[] getdead03()
    {
        return dead03;
    }
    
    static Image getpause()
    {
     return pause;
    }

    static Image getcontinue()
    {
     return conTinue;
    }

    static Image getrestart()
    {
     return restart;
    }

    static Image getdel()
    {
     return del;
    }

    private static Image[] getImagearray(String baseUrl) {
    Image[] images = new Image[36];
    for (int i = 0; i < images.length; i++) {
        String imagePath = baseUrl + String.format("%02d", i) + ".png";
        InputStream stream = Unit.class.getResourceAsStream(imagePath);
        if (stream == null) {
            throw new RuntimeException("Cannot find image: " + imagePath);
        }
        images[i] = new Image(stream);
    }
    return images;
}
    } 
