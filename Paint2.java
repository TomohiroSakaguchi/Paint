import java.util.*;
import java.awt.*;
import java.awt.event.*;
@SuppressWarnings("unchecked")
class Paint2 extends Frame implements MouseListener, MouseMotionListener {
  int x, y, size,count = 0;
  Vector objList;
  Figure obj;
  public static void main(String[] args) {
    Paint2 f = new Paint2();
    f.setSize(640, 480);
    f.setTitle("Paint Sample");
    f.addWindowListener(new WindowAdapter(){
      @Override public void windowClosing(WindowEvent e){
        System.exit(0);
      }});
    f.setVisible(true);
  }
  Paint2(){
    objList = new Vector();
    addMouseListener(this);
    addMouseMotionListener(this);
  }
  @Override public void paint(Graphics g){
    Figure f;
    for (int i = 0; i < objList.size();i++ ) {
      f = (Figure)objList.elementAt(i);
      f.paint(g);
      //System.out.println(objList.size());
    }
    if(obj != null) obj.paint(g);
  }
  public void mousePressed(MouseEvent e){
    x = e.getX();
    y = e.getY();
    if (count % 2 == 0) {
      size = 10;
    } else {
      size = 150;
    }
    obj = new Circle(size);
    System.out.println("the size of circle is "+size);
    obj.moveto(x, y);
    repaint();
    count++;
  }
  public void mouseReleased(MouseEvent e){
    x = e.getX();
    y = e.getY();
    obj.moveto(x, y);
    objList.add(obj);
    if (objList.size() > 100) {
      objList.remove(0);
    }
    System.out.println("the number of circle is "+objList.size());
    obj = null;
    repaint();
  }
  public void mouseClicked(MouseEvent e){}
  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  public void mouseDragged(MouseEvent e){
    x = e.getX();
    y = e.getY();
    if (obj != null) obj.moveto(x, y);
    repaint();
  }
  @Override public void mouseMoved(MouseEvent e){}
}

class Coord{
  int x, y;
  Coord(){
    x = y = 0;
  }
  public void move(int dx, int dy){
    x += dx;
    y += dy;
  }
  public void moveto(int x, int y){//絶対座標で移動させる
    this.x = x; //左辺はclassの直後に定義したx，右辺は引数のx
    this.y = y; //左辺はclassの直後に定義したy，右辺は引数のy
  }
  public void paint(Graphics g){}
}

class Figure extends Coord{
  int color;
  Figure(){
    color = 0;
  }
  public void paint(Graphics g){}
}
class Circle extends Figure{
  int size;
  Circle(int size){
    color = 0;
    this.size = size;
  }
  @Override public void paint(Graphics g){
    g.drawOval(x-size/2, y-size/2, size, size);
  }
}
