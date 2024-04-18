package tank;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;

import static tank.Direction.*;

public class Bullet extends Role {

    public Bullet(double x, double y, Group group, Direction dir, GameScene gameScene) {
        super(x, y, 0, 0, group, dir, gameScene);
        speed = 10;
        if (dir.equals(up) || dir.equals(down)) {
            width = 10;
            height = 22;
        } else if (dir.equals(left) || dir.equals(right)) {
            height = 10;
            width = 22;
        }

        if (group.equals(Group.green)) {
            switch (dir) {
                case up:
                    image = new Image(Bullet.class.getResourceAsStream("/config/image/bullet-green-up.png"));
                    break;
                case down:
                    image = new Image(Bullet.class.getResourceAsStream("/config/image/bullet-green-down.png"));
                    break;
                case left:
                    image = new Image(Bullet.class.getResourceAsStream("/config/image/bullet-green-left.png"));
                    break;
                case right:
                    image = new Image(Bullet.class.getResourceAsStream("/config/image/bullet-green-right.png"));
                    break;
            }
        } else {
            switch (dir) {
                case up:
                    image = new Image(Bullet.class.getResourceAsStream("/config/image/bullet-red-up.png"));
                    break;
                case down:
                    image = new Image(Bullet.class.getResourceAsStream("/config/image/bullet-red-down.png"));
                    break;
                case left:
                    image = new Image(Bullet.class.getResourceAsStream("/config/image/bullet-red-left.png"));
                    break;
                case right:
                    image = new Image(Bullet.class.getResourceAsStream("/config/image/bullet-red-right.png"));
                    break;
            }
        }
    }

    @Override
    public void move() {
        switch (dir) {
            case up:
                y -= speed;
                break;
            case down:
                y += speed;
                break;
            case left:
                x -= speed;
                break;
            case right:
                x += speed;
                break;
        }


//        if(x > Director.WIDTH - width - 5) x = Director.WIDTH - width - 5;
//        if(y > Director.HEIGHT - height - 30) y = Director.HEIGHT - height - 30;
        if (x < 0 || y < 0 || x > Director.WIDTH || y > Director.HEIGHT) {
            gameScene.bullets.remove(this);
        }
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (!alive) {
            gameScene.bullets.remove(this);
            gameScene.explodes.add(new Explode(x, y, gameScene));
            SoundEffect.play("/config/sound/explosion.wav");
            return;
        }
        super.paint(graphicsContext);
        move();
    }

    public boolean impactTank(Tank tank) {
        if (tank != null && !tank.group.equals(this.group) && getContour().intersects(tank.getContour())) {
            tank.setAlive(false);
            alive = false;
            return true;
        }
        return false;
    }

    public void impactTank(List<Tank> tanks) {
        for (Tank t : tanks) {
            impactTank(t);
        }
    }

    public boolean impactCrate(Crate crate) {
        if (crate != null && getContour().intersects(crate.getContour())) {
            alive = false;
            gameScene.crates.remove(crate);
            return true;
        }
        return false;
    }

    public void impactCrates(List<Crate> crates) {

        for (int i = 0; i < crates.size(); i++) {
            Crate crate = crates.get(i);
            impactCrate(crate);
        }
    }

    public boolean impactRock(Rock rock) {
        if (rock != null && getContour().intersects(rock.getContour())) {
            alive = false;
            return true;
        }
        return false;
    }

    public void impactRocks(List<Rock> rocks) {

        for (int i = 0; i < rocks.size(); i++) {
            Rock rock = rocks.get(i);
            impactRock(rock);
        }
    }
}
