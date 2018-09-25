/*
 * {{{ header & license
 * Copyright (c) 2006 Wisconsin Court System
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.	See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 * }}}
 */
package org.docx4j.convert.in.xhtml;

import java.awt.Rectangle;

import com.openhtmltopdf.extend.FSGlyphVector;
import com.openhtmltopdf.extend.FontContext;
import com.openhtmltopdf.extend.OutputDevice;
import com.openhtmltopdf.extend.TextRenderer;
import com.openhtmltopdf.render.FSFont;
import com.openhtmltopdf.render.FSFontMetrics;
import com.openhtmltopdf.render.JustificationInfo;

import org.apache.fontbox.ttf.TrueTypeCollection;
import org.apache.fontbox.ttf.TrueTypeCollection.TrueTypeFontProcessor;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontDescriptor;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Docx4jTextRenderer implements TextRenderer {
    private static float TEXT_MEASURING_DELTA = 0.01f;
    
    public void setup(FontContext context) {
    }

    public void drawString(OutputDevice outputDevice, String string, float x, float y) {
        ((Docx4jDocxOutputDevice)outputDevice).drawString(string, x, y, null);
    }
    
    public void drawString(
            OutputDevice outputDevice, String string, float x, float y, JustificationInfo info) {
        ((Docx4jDocxOutputDevice)outputDevice).drawString(string, x, y, info);
    }

    public FSFontMetrics getFSFontMetrics(FontContext context, FSFont font, String string) {
        //Docx4jFontDescription descr = ((Docx4jFSFont)font).getFontDescription();
        Docx4jFontDescription descr = new Docx4jFontDescription((PDFont)PDType1Font.COURIER);
        PDFont bf = descr.getFont();
        // float size = font.getSize2D();
        float size = 14.0f; 
        Docx4jFSFontMetrics result = new Docx4jFSFontMetrics();
        
        result.setAscent(bf.getFontDescriptor().getAscent());
        result.setDescent(-bf.getFontDescriptor().getDescent());
        
        result.setStrikethroughOffset(-descr.getYStrikeoutPosition() / 1000f * size);
        if (descr.getYStrikeoutSize() != 0) {
            result.setStrikethroughThickness(descr.getYStrikeoutSize() / 1000f * size);
        } else {
            result.setStrikethroughThickness(size / 12.0f);
        }
        
        result.setUnderlineOffset(-descr.getUnderlinePosition() / 1000f * size);
        result.setUnderlineThickness(descr.getUnderlineThickness() / 1000f * size);
        
        return result;
    }

    public int getWidth(FontContext context, FSFont font, String string) {
        //PDFont bf = ((Docx4jFSFont)font).getFontDescription().getFont();
        PDFont bf = (PDFont)PDType1Font.COURIER;
        float result = bf.getFontDescriptor().getMaxWidth();
        if (result - Math.floor(result) < TEXT_MEASURING_DELTA) {
            return (int)result;
        } else {
            return (int)Math.ceil(result); 
        }
    }

    public void setFontScale(float scale) {
    }

    public float getFontScale() {
        return 1.0f;
    }

    public void setSmoothingThreshold(float fontsize) {
    }

    public int getSmoothingLevel() {
        return 0;
    }

    public void setSmoothingLevel(int level) {
    }

    public Rectangle getGlyphBounds(OutputDevice outputDevice, FSFont font, FSGlyphVector fsGlyphVector, int index, float x, float y) {
        throw new UnsupportedOperationException();
    }

    public float[] getGlyphPositions(OutputDevice outputDevice, FSFont font, FSGlyphVector fsGlyphVector) {
        throw new UnsupportedOperationException();
    }

    public FSGlyphVector getGlyphVector(OutputDevice outputDevice, FSFont font, String string) {
        throw new UnsupportedOperationException();
    }

    public void drawGlyphVector(OutputDevice outputDevice, FSGlyphVector vector, float x, float y) {
        throw new UnsupportedOperationException();
    }
}
