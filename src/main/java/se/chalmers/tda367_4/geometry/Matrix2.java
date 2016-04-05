package se.chalmers.tda367_4.geometry;

public class Matrix2 {
    private final static float[] IDENTITY = {
            1, 0,
            0, 1
    };

    private float[] m;

    public static Matrix2 fromRows(Vector2[] rows) {
        Matrix2 m = new Matrix2();
        for(int i = 0; i < 2; i++) {
            Vector2 v = rows[i];
            m.set(v.getX(), 0, i);
            m.set(v.getY(), 1, i);
        }
        return m;
    }
    public static Matrix2 fromColumns(Vector2[] columns) {
        Matrix2 m = new Matrix2();
        for(int i = 0; i < 2; i++) {
            Vector2 v = columns[i];
            m.set(v.getX(), i, 0);
            m.set(v.getY(), i, 1);
        }
        return m;
    }

    public Matrix2(float[] m) {
        this.m = new float[2 * 2];
        for(int i = 0; i < this.m.length; i++) {
            this.m[i] = m[i];
        }
    }
    public Matrix2() {
        this(IDENTITY);
    }
    public Matrix2(Matrix2 other) {
        this(other.m);
    }
    public int index(int x, int y) {
        return x + y * 2;
    }
    public float get(int x, int y) {
        return m[index(x, y)];
    }
    public void set(float v, int x, int y) {
        m[index(x, y)] = v;
    }

    public Vector2 multiply(Vector2 vector) {
        float[] v = new float[2];
        for(int i = 0; i < 2; i++) {
            v[i] =
                    get(0, i) * vector.getX() +
                    get(1, i) * vector.getY();
        }
        return new Vector2(v[0], v[1]);
    }
    public Matrix2 multiply(Matrix2 other) {
        Matrix2 dst = new Matrix2();
        for(int x = 0; x < 2; x++) {
            for(int y = 0; y < 2; y++) {
                float sum = 0;
                for(int i = 0; i < 2; i++) {
                    sum += get(i, y) * other.get(x, i);
                }
                dst.set(sum, x, y);
            }
        }
        return dst;
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int y = 0; y < 2; y++) {
            for(int x = 0; x < 2; x++) {
                builder.append(get(x, y)).append(' ');
            }
            builder.append('\n');
        }
        return builder.toString();
    }
    public Matrix3 toAffine() {
        return new Matrix3(new float[] {
                m[0], m[1], 0,
                m[2], m[3], 0,
                0, 0, 1
        });
    }
    public Matrix2 invert() {
        float d = determinant();
        return new Matrix2(new float[] {
                 m[3] / d, -m[1] / d,
                -m[2] / d,  m[0] / d
        });
    }
    public float determinant() {
        return m[0] * m[3] - m[1] * m[2];
    }
    public boolean equals(Matrix2 other, float delta) {
        for(int i = 0; i < m.length; i++) {
            if(Math.abs(m[i] - other.m[i]) > delta) {
                return false;
            }
        }
        return true;
    }
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }
        else if(other == null) {
            return false;
        }
        else if(this.getClass() != other.getClass()) {
            return false;
        }
        else {
            return equals((Matrix2)other, 0);
        }
    }
    public Matrix2 clone() {
        return new Matrix2(this);
    }
}
