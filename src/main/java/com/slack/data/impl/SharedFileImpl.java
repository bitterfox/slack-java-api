/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.ChannelId;
import com.slack.data.GroupId;
import com.slack.data.SharedFile;
import com.slack.data.SharedFileComment;
import com.slack.data.SharedFileId;
import com.slack.data.UserId;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.activation.MimeType;

/**
 *
 * @author bitter_fox
 */
public class SharedFileImpl implements SharedFile
{
    private SharedFileId id;
    private int created;
    private int timestamp;
    private String name;
    private String title;
    private MimeType mimeType;
    private String fileType;
    private String prettyType;
    private UserId user;
    private Mode mode;
    private boolean isEditable;
    private boolean isExternal;
    private String externalType;
    private int size;
    private URL url;
    private Optional<URL> urlDownload;
    private URL urlPrivate;
    private Optional<URL> urlPrivateDownload;
    private URL permalink;
    private Optional<URL> permalinkPublic;
    private URL editLink;
    private String preview;
    private String previewHighlight;
    private int lines;
    private int linesMore;
    private boolean isPublic;
    private boolean isPublicUrlShared;
    private List<ChannelId> sharedChannels;
    private List<GroupId> sharedGroups;
    private int commentsCount;
    private Optional<SharedFileComment> initialComment = Optional.empty();
    private int numStars;
    private boolean isStarred;

    private Map<Thumb.Size, Thumb> thumbs = new HashMap<>();

    public void id(SharedFileId id)
    {
        this.id = id;
    }

    @Override
    public SharedFileId id()
    {
        return id;
    }

    public void created(int created)
    {
        this.created = created;
    }

    @Override
    public int created()
    {
        return created;
    }

    public void timestamp(int timestamp)
    {
        this.timestamp = timestamp;
    }

    @Override
    public int timestamp()
    {
        return timestamp;
    }

    public void name(String name)
    {
        this.name = name;
    }

    @Override
    public String name()
    {
        return name;
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

    public void mimeType(MimeType mimeType)
    {
        this.mimeType = mimeType;
    }

    @Override
    public MimeType mimeType()
    {
        return mimeType;
    }

    public void fileType(String fileType)
    {
        this.fileType = fileType;
    }

    @Override
    public String fileType()
    {
        return fileType;
    }

    public void prettyType(String prettyType)
    {
        this.prettyType = prettyType;
    }

    @Override
    public String prettyType()
    {
        return prettyType;
    }

    public void user(UserId user)
    {
        this.user = user;
    }

    @Override
    public UserId user()
    {
        return user;
    }

    public void mode(Mode mode)
    {
        this.mode = mode;
    }

    @Override
    public Mode mode()
    {
        return mode;
    }

    public void isEditable(boolean isEditable)
    {
        this.isEditable = isEditable;
    }

    @Override
    public boolean isEditable()
    {
        return isEditable;
    }

    public void isExternal(boolean isExternal)
    {
        this.isExternal = isExternal;
    }

    @Override
    public boolean isExternal()
    {
        return isExternal;
    }

    public void externalType(String externalType)
    {
        this.externalType = externalType;
    }

    @Override
    public String externalType()
    {
        return externalType;
    }

    public void size(int size)
    {
        this.size = size;
    }

    @Override
    public int size()
    {
        return size;
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

    public void urlDownload(URL urlDownload)
    {
        this.urlDownload = Optional.of(urlDownload);
    }

    @Override
    public Optional<URL> urlDownload()
    {
        return urlDownload;
    }

    public void urlPrivate(URL urlPrivate)
    {
        this.urlPrivate = urlPrivate;
    }

    @Override
    public URL urlPrivate()
    {
        return urlPrivate;
    }

    public void urlPrivateDownload(URL urlPrivateDownload)
    {
        this.urlPrivateDownload = Optional.of(urlPrivateDownload);
    }

    @Override
    public Optional<URL> urlPrivateDownload()
    {
        return urlPrivateDownload;
    }

    public void permalink(URL permalink)
    {
        this.permalink = permalink;
    }

    @Override
    public URL permalink()
    {
        return permalink;
    }

    public void permalinkPublic(URL permalinkPublic)
    {
        this.permalinkPublic = Optional.of(permalinkPublic);
    }

    @Override
    public Optional<URL> permalinkPublic()
    {
        return permalinkPublic;
    }

    public void editLink(URL editLink)
    {
        this.editLink = editLink;
    }

    @Override
    public URL editLink()
    {
        return editLink;
    }

    public void preview(String preview)
    {
        this.preview = preview;
    }

    @Override
    public String preview()
    {
        return preview;
    }

    public void previewHighlight(String previewHighlight)
    {
        this.previewHighlight = previewHighlight;
    }

    @Override
    public String previewHighlight()
    {
        return previewHighlight;
    }

    public void lines(int lines)
    {
        this.lines = lines;
    }

    @Override
    public int lines()
    {
        return lines;
    }

    public void linesMore(int linesMore)
    {
        this.linesMore = linesMore;
    }

    @Override
    public int linesMore()
    {
        return linesMore;
    }

    public void isPublic(boolean isPublic)
    {
        this.isPublic = isPublic;
    }

    @Override
    public boolean isPublic()
    {
        return isPublic;
    }

    public void setCreated(int created)
    {
        this.created = created;
    }

    public void isPublicUrlShared(boolean isPublicUrlShared)
    {
        this.isPublicUrlShared = isPublicUrlShared;
    }

    @Override
    public boolean isPublicUrlShared()
    {
        return isPublicUrlShared;
    }

    public void sharedChannels(List<ChannelId> sharedChannels)
    {
        this.sharedChannels = sharedChannels;
    }

    @Override
    public List<ChannelId> sharedChannels()
    {
        return sharedChannels;
    }

    public void sharedGroups(List<GroupId> sharedGroups)
    {
        this.sharedGroups = sharedGroups;
    }

    @Override
    public List<GroupId> sharedGroups()
    {
        return sharedGroups;
    }

    public void commentsCount(int commentsCount)
    {
        this.commentsCount = commentsCount;
    }

    @Override
    public int commentsCount()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void initialComment(SharedFileComment initialComment)
    {
        this.initialComment = Optional.of(initialComment);
    }

    @Override
    public Optional<SharedFileComment> initialComment()
    {
        return initialComment;
    }

    public void numStars(int numStars)
    {
        this.numStars = numStars;
    }

    @Override
    public int numStars()
    {
        return numStars;
    }

    public void isStarred(boolean isStarred)
    {
        this.isStarred = isStarred;
    }

    @Override
    public boolean isStarred()
    {
        return isStarred;
    }

    public void thumb(Thumb thumb)
    {
        thumbs.put(thumb.size(), thumb);
    }

    @Override
    public Optional<Thumb> thumb(Thumb.Size size)
    {
        return Optional.ofNullable(thumbs.get(size));
    }

    @Override
    public Map<SharedFile.Thumb.Size,Thumb> thumbs()
    {
        return Collections.unmodifiableMap(thumbs);
    }

    @Override
    public Optional<Thumb> smallestThumb()
    {
        return thumbs.values().stream()
            .sorted(Comparator.comparing(Thumb::size))
            .findFirst();
    }

    @Override
    public Optional<Thumb> largestThumb()
    {
        return thumbs.values().stream()
            .sorted(Comparator.comparing(Thumb::size).reversed())
            .findFirst();
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + this.created;
        hash = 19 * hash + this.timestamp;
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + Objects.hashCode(this.title);
        hash = 19 * hash + Objects.hashCode(this.mimeType);
        hash = 19 * hash + Objects.hashCode(this.fileType);
        hash = 19 * hash + Objects.hashCode(this.prettyType);
        hash = 19 * hash + Objects.hashCode(this.user);
        hash = 19 * hash + Objects.hashCode(this.mode);
        hash = 19 * hash + (this.isEditable ? 1 : 0);
        hash = 19 * hash + (this.isExternal ? 1 : 0);
        hash = 19 * hash + Objects.hashCode(this.externalType);
        hash = 19 * hash + this.size;
        hash = 19 * hash + Objects.hashCode(this.url);
        hash = 19 * hash + Objects.hashCode(this.urlDownload);
        hash = 19 * hash + Objects.hashCode(this.urlPrivate);
        hash = 19 * hash + Objects.hashCode(this.urlPrivateDownload);
        hash = 19 * hash + Objects.hashCode(this.permalink);
        hash = 19 * hash + Objects.hashCode(this.permalinkPublic);
        hash = 19 * hash + Objects.hashCode(this.editLink);
        hash = 19 * hash + Objects.hashCode(this.preview);
        hash = 19 * hash + Objects.hashCode(this.previewHighlight);
        hash = 19 * hash + this.lines;
        hash = 19 * hash + this.linesMore;
        hash = 19 * hash + (this.isPublic ? 1 : 0);
        hash = 19 * hash + (this.isPublicUrlShared ? 1 : 0);
        hash = 19 * hash + Objects.hashCode(this.sharedChannels);
        hash = 19 * hash + Objects.hashCode(this.sharedGroups);
        hash = 19 * hash + this.commentsCount;
        hash = 19 * hash + Objects.hashCode(this.initialComment);
        hash = 19 * hash + this.numStars;
        hash = 19 * hash + (this.isStarred ? 1 : 0);
        hash = 19 * hash + Objects.hashCode(this.thumbs);
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
        final SharedFileImpl other = (SharedFileImpl)obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (this.created != other.created)
        {
            return false;
        }
        if (this.timestamp != other.timestamp)
        {
            return false;
        }
        if (!Objects.equals(this.name, other.name))
        {
            return false;
        }
        if (!Objects.equals(this.title, other.title))
        {
            return false;
        }
        if (!Objects.equals(this.mimeType, other.mimeType))
        {
            return false;
        }
        if (!Objects.equals(this.fileType, other.fileType))
        {
            return false;
        }
        if (!Objects.equals(this.prettyType, other.prettyType))
        {
            return false;
        }
        if (!Objects.equals(this.user, other.user))
        {
            return false;
        }
        if (this.mode != other.mode)
        {
            return false;
        }
        if (this.isEditable != other.isEditable)
        {
            return false;
        }
        if (this.isExternal != other.isExternal)
        {
            return false;
        }
        if (!Objects.equals(this.externalType, other.externalType))
        {
            return false;
        }
        if (this.size != other.size)
        {
            return false;
        }
        if (!Objects.equals(this.url, other.url))
        {
            return false;
        }
        if (!Objects.equals(this.urlDownload, other.urlDownload))
        {
            return false;
        }
        if (!Objects.equals(this.urlPrivate, other.urlPrivate))
        {
            return false;
        }
        if (!Objects.equals(this.urlPrivateDownload, other.urlPrivateDownload))
        {
            return false;
        }
        if (!Objects.equals(this.permalink, other.permalink))
        {
            return false;
        }
        if (!Objects.equals(this.permalinkPublic, other.permalinkPublic))
        {
            return false;
        }
        if (!Objects.equals(this.editLink, other.editLink))
        {
            return false;
        }
        if (!Objects.equals(this.preview, other.preview))
        {
            return false;
        }
        if (!Objects.equals(this.previewHighlight, other.previewHighlight))
        {
            return false;
        }
        if (this.lines != other.lines)
        {
            return false;
        }
        if (this.linesMore != other.linesMore)
        {
            return false;
        }
        if (this.isPublic != other.isPublic)
        {
            return false;
        }
        if (this.isPublicUrlShared != other.isPublicUrlShared)
        {
            return false;
        }
        if (!Objects.equals(this.sharedChannels, other.sharedChannels))
        {
            return false;
        }
        if (!Objects.equals(this.sharedGroups, other.sharedGroups))
        {
            return false;
        }
        if (this.commentsCount != other.commentsCount)
        {
            return false;
        }
        if (!Objects.equals(this.initialComment, other.initialComment))
        {
            return false;
        }
        if (this.numStars != other.numStars)
        {
            return false;
        }
        if (this.isStarred != other.isStarred)
        {
            return false;
        }
        if (!Objects.equals(this.thumbs, other.thumbs))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "SharedFileImpl{" + "id=" + id + ", created=" + created + ", timestamp=" + timestamp + ", name=" + name + ", title=" + title + ", mimeType=" + mimeType + ", fileType=" + fileType + ", prettyType=" + prettyType + ", user=" + user + ", mode=" + mode + ", isEditable=" + isEditable + ", isExternal=" + isExternal + ", externalType=" + externalType + ", size=" + size + ", url=" + url + ", urlDownload=" + urlDownload + ", urlPrivate=" + urlPrivate + ", urlPrivateDownload=" + urlPrivateDownload + ", permalink=" + permalink + ", permalinkPublic=" + permalinkPublic + ", editLink=" + editLink + ", preview=" + preview + ", previewHighlight=" + previewHighlight + ", lines=" + lines + ", linesMore=" + linesMore + ", isPublic=" + isPublic + ", isPublicUrlShared=" + isPublicUrlShared + ", sharedChannels=" + sharedChannels + ", sharedGroups=" + sharedGroups + ", commentsCount=" + commentsCount + ", initialComment=" + initialComment + ", numStars=" + numStars + ", isStarred=" + isStarred + ", thumbs=" + thumbs + '}';
    }
}
