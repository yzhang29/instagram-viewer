package com.yahoo.apps.instagram;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by yzhang29 on 2/6/15.
 */
@Table(name = "ImageData")
public class ImageData extends Model{
    @Column(name = "postId", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public String postId;
    @Column(name = "username")
    public String username;
    @Column(name = "userProfileImageUrl")
    public String userProfileImageUrl;
    @Column(name = "caption")
    public String caption;
    @Column(name = "imageUrl")
    public String imageUrl;
    @Column(name = "imageHeight")
    public int imageHeight;
    @Column(name = "likesCount")
    public long likesCount;

    public ImageData(){
        super();
    }

    public static List<ImageData> getAll() {
        // This is how you execute a query
        return new Select()
                .from(ImageData.class)
                .execute();
    }
}

