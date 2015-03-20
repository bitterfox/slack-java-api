/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data;

import java.awt.Image;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.activation.MimeType;

/**
 *
 * @author bitter_fox
 */
public interface SharedFile
{
    enum Mode
    {
        HOSTED, EXTERNAL, SNIPPET, POST;
    }

    SharedFileId id();
    int created();
    int timestamp();
    String name();
    String title();
    MimeType mimeType();
    String fileType();
    String prettyType();
    UserId user();

    Mode mode();
    boolean isEditable();
    boolean isExternal();
    String externalType();

    int size();

    URL url();
    Optional<URL> urlDownload();
    URL urlPrivate();
    Optional<URL> urlPrivateDownload();

    Optional<Thumb> thumb(Thumb.Size size);
    Map<SharedFile.Thumb.Size,Thumb> thumbs();
    Optional<Thumb> smallestThumb();
    Optional<Thumb> largestThumb();

    URL permalink();
    Optional<URL> permalinkPublic();
    URL editLink();
    String preview();
    String previewHighlight();
    int lines();
    int linesMore();

    boolean isPublic();
    boolean isPublicUrlShared();

    List<ChannelId> sharedChannels();
    List<GroupId> sharedGroups();
    // ims
    int commentsCount();
    Optional<SharedFileComment> initialComment();
    int numStars();
    boolean isStarred();

    interface Thumb
    {
        enum Size
        {
            XS(64),
            S(80),
            M(160),
            L(360),
            XL(720),
            XXL(1024),;

            private int size;

            private Size(int size)
            {
                this.size = size;
            }

            public int getSize()
            {
                return size;
            }
        }

        URL url();
        Size size();
        int width();
        int height();

        Image asAwtImage();
        javafx.scene.image.Image asFxImage();
    }
}
