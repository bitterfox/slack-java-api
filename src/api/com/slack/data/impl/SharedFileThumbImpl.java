/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.SharedFile;
import com.slack.util.Holder;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author bitter_fox
 */
public class SharedFileThumbImpl implements SharedFile.Thumb
{
    private Size size;
    private URL url;
    private OptionalInt width = OptionalInt.empty();
    private OptionalInt height = OptionalInt.empty();
    private Holder<Image> awtImage = new Holder<>();
    private Holder<javafx.scene.image.Image> fxImage = new Holder<>();

    public SharedFileThumbImpl(Size size)
    {
        this.size = size;
    }

    public void url(URL url)
    {
        this.url = url;
    }

    @Override
    public URL url()
    {
        return url;
    }

    public void size(Size size)
    {
        this.size = size;
    }

    @Override
    public Size size()
    {
        return size;
    }

    public void width(int width)
    {
        this.width = OptionalInt.of(width);
    }

    @Override
    public int width()
    {
        return width.orElseGet(size::getSize);
    }

    public void height(int height)
    {
        this.height = OptionalInt.of(height);
    }

    @Override
    public int height()
    {
        return height.orElseGet(size::getSize);
    }

    @Override
    public Image asAwtImage()
    {
        return awtImage.computeIfAbsent(
            () ->
            {
                try
                {
                    return ImageIO.read(url);
                }
                catch (IOException ex)
                {
                    Logger.getLogger(SharedFileThumbImpl.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex);
                }
            });
    }

    @Override
    public javafx.scene.image.Image asFxImage()
    {
        return fxImage.computeIfAbsent(
            () -> new javafx.scene.image.Image(url.toString(), true));
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.size);
        hash = 43 * hash + Objects.hashCode(this.url);
        hash = 43 * hash + Objects.hashCode(this.width);
        hash = 43 * hash + Objects.hashCode(this.height);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final SharedFileThumbImpl other = (SharedFileThumbImpl)obj;
        if (this.size != other.size)
        {
            return false;
        }
        if (!Objects.equals(this.url, other.url))
        {
            return false;
        }
        if (!Objects.equals(this.width, other.width))
        {
            return false;
        }
        if (!Objects.equals(this.height, other.height))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "SharedFileThumbImpl{" + "size=" + size + ", url=" + url + ", width=" + width + ", height=" + height + ", awtImage=" + awtImage + ", fxImage=" + fxImage + '}';
    }
}
