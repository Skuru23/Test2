import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseSQL(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
)
