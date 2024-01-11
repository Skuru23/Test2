import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.test2.model.Course

@Dao
interface CourseDAO {
    @Insert
    suspend fun insertCourse(course: CourseSQL)

    @Query("SELECT * FROM courses")
    suspend fun getAllCourses(): List<CourseSQL>

}