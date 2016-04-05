package se.chalmers.tda367_4.geometry;

public class Matrix3 {
    private final static float[] IDENTITY = {
            1, 0, 0,
            0, 1, 0,
            0, 0, 1
    };

    private float[] m;

    public static Matrix3 fromRows(Vector3[] rows) {
        Matrix3 m = new Matrix3();
        for(int i = 0; i < 3; i++) {
            Vector3 v = rows[i];
            m.set(v.getX(), 0, i);
            m.set(v.getY(), 1, i);
            m.set(v.getZ(), 2, i);
        }
        return m;
    }
    public static Matrix3 fromColumns(Vector3[] columns) {
        Matrix3 m = new Matrix3();
        for(int i = 0; i < 3; i++) {
            Vector3 v = columns[i];
            m.set(v.getX(), i, 0);
            m.set(v.getY(), i, 1);
            m.set(v.getZ(), i, 2);
        }
        return m;
    }

    public Matrix3(float[] m) {
        this.m = new float[3 * 3];
        for(int i = 0; i < this.m.length; i++) {
            this.m[i] = m[i];
        }
    }
    public Matrix3() {
        this(IDENTITY);
    }
    public int index(int x, int y) {
        return x + y * 3;
    }
    public float get(int x, int y) {
        return m[index(x, y)];
    }
    public void set(float v, int x, int y) {
        m[index(x, y)] = v;
    }

    public Vector3 multiply(Vector3 vector) {
        float[] v = new float[3];
        for(int i = 0; i < 3; i++) {
            v[i] =
                    get(0, i) * vector.getX() +
                    get(1, i) * vector.getY() +
                    get(2, i) * vector.getZ();
        }
        return new Vector3(v[0], v[1], v[2]);
    }
    public Matrix3 multiply(Matrix3 other) {
        Matrix3 dst = new Matrix3();
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                float sum = 0;
                for(int i = 0; i < 3; i++) {
                    sum += get(i, y) * other.get(x, i);
                }
                dst.set(sum, x, y);
            }
        }
        return dst;
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 3; x++) {
                builder.append(get(x, y)).append(' ');
            }
            builder.append('\n');
        }
        return builder.toString();
    }
    public static Matrix3 createTranslation(Vector2 vector) {
        return new Matrix3(new float[] {
                1, 0, vector.getX(),
                0, 1, vector.getY(),
                0, 0, 1
        });
    }
}
