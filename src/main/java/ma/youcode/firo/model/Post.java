package ma.youcode.firo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Post {
    @Id
    private Long id;

}
