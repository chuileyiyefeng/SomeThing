package com.example.something.life_observer;

public class MyStart {
    public static void main(String[] args)
    {
        int LineNum=10;
        int LineMax=3;
        for (int x=-LineNum;x<=LineNum;x++)
        {
            for (int y=-LineNum;y<=LineNum;y++)
            {
                if (Math.abs(x)+Math.abs(y)<=LineMax)
                {
                    System.out.print("*");
                }
                else
                {
                    System.out.print("  ");
                }
            }
            System.out.print("\n");
        }

    }
}
