/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author abdotalaat
 */
public class Advertise  implements Serializable{
    
    
    String title;
    ImageIcon icon;
    String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    
    
    
    
    
}
