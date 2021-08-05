package hcy.gym.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString(exclude = {"post", "member"})
@Table(name = "comment_t")
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String content;

    //== 답글을 위한 속성 ==//
    private Integer deep; // 모 댓글 : 0, 대댓글 : 1

    private Long motherId; // 모 댓글

    private Integer subOrder; // 대댓글이 여러개일 때 순서

    private Integer reCommentNum; // 본인이 모 댓글일 때 대댓글의 수. 대댓글 order에 사용.

    public void changeContent(String content) {
        this.content = content;
    }

    // 대댓글 수 늘리는 메서드.
    public void addReCommentNum() {
        this.reCommentNum++;
    }

    // == 연관관계 메서드 == /
    public void setPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }
}
