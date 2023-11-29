import axios from "axios";
import Course from "../dtos/Course";

const API_URL = "http://localhost:8086/api";

export async function getCourses(): Promise<Course[]> {
  const res = await axios.get<{ code: string; data: { content: Course[] } }>(
    `${API_URL}/course/`
  );

  if (res.status === 200) {
    if (res.data.code === "SUCCESS") {
      return res.data.data.content;
    }
    throw new Error(`Request end with code error ${res.status}`);
  }

  throw new Error(`Request end with status error ${res.status}`);
}

export async function newCourse(course: Omit<Course, "id">): Promise<void> {
  const res = await axios.post<{ code: string; data: { content: Course[] } }>(
    `${API_URL}/course/`,
    course
  );

  if (res.status !== 200) {
    throw new Error(`Request end with status error ${res.status}`);
  }

  if (res.data.code !== "SUCCESS") {
    throw new Error(`Request end with code error ${res.status}`);
  }
}
