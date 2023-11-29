import { useCallback, useEffect, useState } from "react";
import { getCourses, newCourse } from "../services/api";
import Course from "../dtos/Course";
import NewCource from "./NewCource";

function ListCourses() {
  const [courses, setCourses] = useState<Course[]>([]);
  const [creationPending, setCreationPending] = useState(false);
  const [formVisible, setFormVisible] = useState(false);
  const fetchCourses = useCallback(() => {
    getCourses()
      .then((result) => {
        setCourses(result);
      })
      .catch((err) => {
        alert("Error: " + err.message);
      });
  }, []);

  useEffect(() => {
    fetchCourses();
  }, [fetchCourses]);

  const createCourse = useCallback(
    (course: Omit<Course, "id">) => {
      newCourse(course)
        .then(() => {
          fetchCourses();
          setCreationPending(false);
          setFormVisible(false);
        })
        .catch((err) => {
          setCreationPending(false);
          alert("Error: " + err.message);
        });
    },
    [fetchCourses]
  );

  return (
    <div>
      <div style={{ marginBottom: 25 }}>
        {formVisible ? (
          <NewCource
            onCreate={createCourse}
            creationPending={creationPending}
          />
        ) : (
          <button onClick={() => setFormVisible(true)}>New course</button>
        )}
      </div>
      <table>
        <thead>
          <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Price</td>
            <td>Description</td>
          </tr>
        </thead>
        <tbody>
          {courses.map((it) => (
            <tr key={it.id}>
              <td>{it.id}</td>
              <td>{it.name}</td>
              <td>{it.price}</td>
              <td>{it.description}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ListCourses;
