/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data;

import java.awt.Image;
import java.net.URL;
import java.util.Optional;

/**
 *
 * @author bitter_fox
 */
public interface Profile
{
    public enum ImageSize
    {
        XS(24),
        S(32),
        M(48),
        L(72),
        XL(192),
        ORIGINAL;

        private int size;

        private ImageSize() {}

        private ImageSize(int size)
        {
            this.size = size;
        }

        public int getSize()
        {
            return size;
        }
    }

    Optional<URL> image(ImageSize size);
    Optional<Image> imageAsAwt(ImageSize size);
    Optional<javafx.scene.image.Image> imageAsFx(ImageSize size);

    Optional<URL> anySmallerImage();
    Optional<Image> anySmallerImageAsAwt();
    Optional<javafx.scene.image.Image> anySmallerImageAsFx();

    Optional<URL> anyBiggerImage();
    Optional<Image> anyBiggerImageAsAwt();
    Optional<javafx.scene.image.Image> anyBiggerImageAsFx();

    String firstName();
    String lastName();
    String title();
    String skype();
    String phone();
    String realName();
    String realNameNormalized();
    String email();
}
