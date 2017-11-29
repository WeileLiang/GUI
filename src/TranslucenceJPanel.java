/* 
 *��д�������ʵ�ְ�͸����JPanel  
 */  
import java.awt.AlphaComposite;  
import java.awt.Graphics;  
import java.awt.Graphics2D;  
import java.awt.image.BufferedImage;  
  
import javax.swing.JPanel;  
  
public class TranslucenceJPanel extends JPanel {  
      
    private float transparency=0.1f;  
      
    public TranslucenceJPanel(){  
    }  
      
      
      
    /**���������������JPanel��͸���� 
     *  
     * @param transparency:͸���� 
     *  
     * @return void 
     */  
    public void setTransparent(float transparency) {  
        this.transparency = transparency;  
        
    }  
      
    @Override  
    protected void paintComponent(Graphics g){  
        super.paintComponent(g);  
          
            Graphics2D graphics2d = (Graphics2D) g.create();  
              
            graphics2d.setComposite(AlphaComposite.SrcOver.derive(transparency));  
              
              
            graphics2d.setColor(getBackground());  
            graphics2d.fillRect(0, 0, getWidth(), getHeight());  
              
//          graphics2d.drawImage(background, 0, 0, getWidth(), getHeight(), 46, 114, 315, 521, this);  
              
            graphics2d.dispose();  
    }  
      
}  