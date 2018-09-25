package org.docx4j.convert.in.xhtml;

//import org.docx4j.org.xhtmlrenderer.extend.FSImage;
//import com.lowagie.text.Image;

public class Docx4jFSImage { 
//  implements FSImage, Cloneable {
  
  byte[] image;
  public byte[] getBytes() {
      return image;
  }
  
  public Docx4jFSImage(byte[] bytes) {
      image = bytes;
  }

//  public int getWidth() {
//      throw new NotImplementedException();
//  }
//
//  public int getHeight() {
//      throw new NotImplementedException();
//  }
//
//  public void scale(int width, int height) {
//      throw new NotImplementedException();
//  }
//
//  public Image getImage() {
//      throw new NotImplementedException();
//  }
//
//  public Object clone() {
//      throw new NotImplementedException();
//  }
}
