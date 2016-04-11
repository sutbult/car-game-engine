package se.chalmers.tda367_4.geometry;

public class TriangleImpl implements Triangle {

    private final static Matrix3 EDGE_MATRIX = new Matrix3(new float[] {
             1,  0,  0,
             0,  1,  0,
            -1, -1,  1
    });

    private final Vector2[] corners;
    private final Matrix3 transformation;

    public TriangleImpl(Vector2 a, Vector2 b, Vector2 c) {
        corners = new Vector2[3];
        corners[0] = a;
        corners[1] = b;
        corners[2] = c;

        Matrix3 translation = Matrix3.createTranslation(a.negate());
        Matrix2 base = Matrix2.fromColumns(new Vector2[] {
                b.subtract(a),
                c.subtract(a)
        });
        Matrix3 base3 = base.invert().asAffine();
        transformation = EDGE_MATRIX.multiply(base3).multiply(translation);
    }
    public TriangleImpl(Vector2[] corners) {
        this(corners[0], corners[1], corners[2]);
    }

    public Vector2[] getCorners() {
        return corners.clone();
    }

    private boolean validateEdgeMatrix(Matrix3 m) {
        for(int x = 0; x < 3; x++) {
            boolean hit = true;
            for(int y = 0; y < 3; y++) {
                if(m.get(x, y) < 0) {
                    hit = false;
                    break;
                }
            }
            if(hit) {
                return true;
            }
        }
        return false;
    }
    private boolean cornersInside(Triangle other) {
        Matrix3 corners = Matrix3.fromAffineColumns(other.getCorners());
        corners = transformation.multiply(corners);
        return validateEdgeMatrix(corners);
    }
    public boolean intersects(Triangle other) {
        if(!(other instanceof TriangleImpl)) {
            other = new TriangleImpl(other.getCorners());
        }
        return
                cornersInside(other) ||
                ((TriangleImpl)other).cornersInside(this);
    }
}
