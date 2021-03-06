/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.Profile;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class ProfileImpl implements Profile
{
    private Map<ImageSize, URL> images = new HashMap<>();
    private Map<URL, Image> imagesAwt = new HashMap<>();
    private Map<URL, javafx.scene.image.Image> imagesFx = new HashMap<>();
    private String firstName;
    private String lastName;
    private String title;
    private String skype;
    private String phone;
    private String realName;
    private String realNameNormalized;
    private String email;

    public void image(ImageSize size, URL url)
    {
        images.put(size, url);
    }
    public void image(ImageSize size, Optional<URL> urlOpt)
    {
        urlOpt.ifPresent(url -> images.put(size, url));
    }

    @Override
    public Optional<URL> image(ImageSize size)
    {
        return images.containsKey(size) ? Optional.of(images.get(size)) : Optional.empty();
    }
    @Override
    public Optional<URL> anySmallerImage()
    {
        return images.keySet().stream()
            .sorted(Comparator.naturalOrder())
            .findFirst()
            .map(images::get);
    }
    @Override
    public Optional<URL> anyBiggerImage()
    {
        return images.keySet().stream()
            .sorted(Comparator.reverseOrder())
            .findFirst()
            .map(images::get);
    }

    private Image imageAsAwt(URL url)
    {
        return imagesAwt.computeIfAbsent(url,
            arg ->
            {
                try
                {
                    return ImageIO.read(arg);
                }
                catch (IOException ex)
                {
                    Logger.getLogger(ProfileImpl.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex);
                }
            });
    }
    private Optional<Image> imageAsAwt(Supplier<Optional<URL>> supplier)
    {
        return supplier.get().map(this::imageAsAwt);
    }

    @Override
    public Optional<Image> imageAsAwt(ImageSize size)
    {
        return this.imageAsAwt(() -> this.image(size));
    }
    @Override
    public Optional<Image> anySmallerImageAsAwt()
    {
        return this.imageAsAwt(this::anySmallerImage);
    }
    @Override
    public Optional<Image> anyBiggerImageAsAwt()
    {
        return this.imageAsAwt(this::anyBiggerImage);
    }


    private javafx.scene.image.Image imageAsFx(URL url)
    {
        return new javafx.scene.image.Image(url.toString(), true);
    }
    private Optional<javafx.scene.image.Image> imageAsFx(Supplier<Optional<URL>> supplier)
    {
        return supplier.get().map(this::imageAsFx);
    }

    @Override
    public Optional<javafx.scene.image.Image> imageAsFx(ImageSize size)
    {
        return this.imageAsFx(() -> this.image(size));
    }
    @Override
    public Optional<javafx.scene.image.Image> anySmallerImageAsFx()
    {
        return this.imageAsFx(this::anySmallerImage);
    }
    @Override
    public Optional<javafx.scene.image.Image> anyBiggerImageAsFx()
    {
        return this.imageAsFx(this::anyBiggerImage);
    }

    public void firstName(String firstName)
    {
        this.firstName = firstName;
    }

    @Override
    public String firstName()
    {
        return firstName;
    }

    public void lastName(String lastName)
    {
        this.lastName = lastName;
    }

    @Override
    public String lastName()
    {
        return lastName;
    }

    public void title(String title)
    {
        this.title = title;
    }

    @Override
    public String title()
    {
        return title;
    }

    public void skype(String skype)
    {
        this.skype = skype;
    }

    @Override
    public String skype()
    {
        return skype;
    }

    public void phone(String phone)
    {
        this.phone = phone;
    }

    @Override
    public String phone()
    {
        return phone;
    }

    public void realName(String realName)
    {
        this.realName = realName;
    }

    @Override
    public String realName()
    {
        return realName;
    }

    public void realNameNormalized(String realNameNormalized)
    {
        this.realNameNormalized = realNameNormalized;
    }

    @Override
    public String realNameNormalized()
    {
        return realNameNormalized;
    }

    public void email(String email)
    {
        this.email = email;
    }

    @Override
    public String email()
    {
        return email;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.images);
        hash = 53 * hash + Objects.hashCode(this.firstName);
        hash = 53 * hash + Objects.hashCode(this.lastName);
        hash = 53 * hash + Objects.hashCode(this.title);
        hash = 53 * hash + Objects.hashCode(this.skype);
        hash = 53 * hash + Objects.hashCode(this.phone);
        hash = 53 * hash + Objects.hashCode(this.realName);
        hash = 53 * hash + Objects.hashCode(this.realNameNormalized);
        hash = 53 * hash + Objects.hashCode(this.email);
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
        final ProfileImpl other = (ProfileImpl)obj;

        return Objects.equals(this.images, other.images) &&
            Objects.equals(this.firstName, other.firstName) &&
            Objects.equals(this.lastName, other.lastName) &&
            Objects.equals(this.title, other.title) &&
            Objects.equals(this.skype, other.skype) &&
            Objects.equals(this.phone, other.phone) &&
            Objects.equals(this.realName, other.realName) &&
            Objects.equals(this.realNameNormalized, other.realNameNormalized) &&
            Objects.equals(this.email, other.email);
    }

    @Override
    public String toString()
    {
        return "ProfileImpl{" + "images=" + images + ", imagesAwt=" + imagesAwt + ", imagesFx=" + imagesFx + ", firstName=" + firstName + ", lastName=" + lastName + ", title=" + title + ", skype=" + skype + ", phone=" + phone + ", realName=" + realName + ", realNameNormalized=" + realNameNormalized + ", email=" + email + '}';
    }
}
