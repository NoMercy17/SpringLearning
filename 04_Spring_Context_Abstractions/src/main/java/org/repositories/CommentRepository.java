package org.repositories;

import org.model.Comment;

public interface CommentRepository {
    void storeComment(Comment comment);
}
