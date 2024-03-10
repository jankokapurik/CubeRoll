public class Cube {
    private int bottom;
    private int top;
    private int left;
    private int right;
    private int up;
    private int down;

    public Cube()
    {
        this.top = 6;
        this.bottom = 1;
        this.left = 3;
        this.right = 5;
        this.up = 3;
        this.down = 4;
    }

    void printCube()
    {
        System.out.println("  " + up);
        System.out.println("  ^");
        System.out.println(left + "<" + top + ">" + right);
        System.out.println("  ↓");
        System.out.println("  " + down);
    }

    public int getTopSide() {
        return top;
    }

    public void rollLeft()
    {
        int buffer = this.top;
        this.top = this.right;
        this.right = this.bottom;
        this.bottom = this.left;
        this.left = buffer;
    }

    public void rollRight()
    {
        int buffer = this.top;
        this.top = this.left;
        this.left = this.bottom;
        this.bottom = this.right;
        this.right = buffer;
    }

    public void rollUp()
    {
        int buffer = this.top;
        this.top = this.down;
        this.down = this.bottom;
        this.bottom = this.up;
        this.up= buffer;
    }

    public void rollDown()
    {
        int buffer = this.top;
        this.top = this.up;
        this.up = this.bottom;
        this.bottom = this.down;
        this.down = buffer;
    }

}
