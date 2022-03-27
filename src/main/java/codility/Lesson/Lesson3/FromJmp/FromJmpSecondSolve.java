package codility.Lesson.Lesson3.FromJmp;

public class FromJmpSecondSolve {

    public int solution(int X, int Y, int D) {

        return (Y - X) % D == 0 ? (Y - X) / D : (Y - X) / D + 1;
    }
}
