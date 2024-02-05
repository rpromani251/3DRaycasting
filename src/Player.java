package src;

import java.awt.Color;
import java.awt.Graphics;

import util.Vec2;

public class Player extends Vec2 {
    public double dx, dy, theta;
    public static int mapX, mapY, mapS;

    private final double PI = Math.PI, P2 = Math.PI/2, P3 = 3*Math.PI/2;
    private final double DR = 0.0174533; // one degree in radians

    private final int RED=255, GREEN=255, BLUE=255;

    // Constructor
    public Player(double x, double y) {
        super(x, y);
    }
    public Player(Vec2 vec2) {
        super(vec2);
    }

    private int FixAng(int a) { if(a>359){ a-=360; } if(a<0){ a+=360; } return a;}

    public void draw(Graphics g) {
        // 8x8 Yellow Cube to Represent the Player
        g.setColor(new Color(255, 255, 0));
        g.fillRect((int) x-4, (int) y-4, 8, 8); 
        g.drawLine((int) x, (int) y, (int) (x+dx*5), (int) (y+dy*5));

        // Draw Rays
        int rayColor = 255;
        int mx, my, mp=1, dof, mh=0, mv=0; double ra=0, xo=0, yo=0, rx=0, ry=0, distH, distY, dist=0;
        Vec2 v = new Vec2(), hv = new Vec2(), yv = new Vec2(); 
        ra = theta-DR*30; if(ra<0) { ra+=2*PI; } if(ra>2*PI) { ra-=2*PI; }; // move back 30 degrees and apply limits of [0, 2pi]
        for (int r = 0; r<60; r++) {

            //---Check Horizontal Lines---
            dof = 0;
            double aTan = -1/Math.tan(ra);
                 if(ra>PI) { ry=(((int)y>>6)<<6)-0.0001; rx=(y-ry)*aTan+x; yo=-64; xo=-yo*aTan; } // looking up
            else if(ra<PI) { ry=(((int)y>>6)<<6)+64;     rx=(y-ry)*aTan+x; yo= 64; xo=-yo*aTan; } // looking down
            else { rx=x; ry=y; dof=8; } // looking straight left or right
            while(dof<8) {
                mx = (int)(rx)>>6; my = (int)(ry)>>6; mp=my*mapX+mx;
                if(mp>0 && mp<mapX*mapY && Map.map[mp]>0) { mh=Map.map[mp]; hv.x=rx; hv.y=ry; dof=8; } // hit horizontal wall
                else { rx+=xo; ry+=yo; dof+=1; } // next line
            }

            //---Check Vertical Lines---
            dof = 0;
            double nTan = -Math.tan(ra);
                 if(ra>P2 && ra<P3) { rx=(((int)x>>6)<<6)-0.0001; ry=(x-rx)*nTan+y; xo=-64; yo=-xo*nTan; } // looking left
            else if(ra<P2 || ra>P3) { rx=(((int)x>>6)<<6)+64;     ry=(x-rx)*nTan+y; xo= 64; yo=-xo*nTan; } // looking right
            else { rx=x; ry=y; dof=8; } // looking straight up or down
            while(dof<8) {
                mx = (int)(rx)>>6; my = (int)(ry)>>6; mp=my*mapX+mx;
                if(mp>0 && mp<mapX*mapY && Map.map[mp]>0) { mv=Map.map[mp]; yv.x=rx; yv.y=ry; dof=8; } // hit vertical wall
                else { rx+=xo; ry+=yo; dof+=1; } // next line
            }

            //---Draw Shortest Line---
            distH = dist(new Vec2(x, y), hv); // Distance from player to horizontal intersection
            distY = dist(new Vec2(x, y), yv); // Distance from player to vertical intersection

            if (distY<distH) {v=yv; dist=distY; g.setColor(new Color(RED-15, 0, 0));} if (mv==2) g.setColor(new Color(0, 0, BLUE-15));// Vertical Wall Hit
            else if (distH<distY) { v=hv; dist=distH; g.setColor(new Color(RED-65, 0, 0));} if (mh==2) g.setColor(new Color(0, 0, BLUE-65));// Horizontal Wall hit
            // else if (distH<distY) {}

            g.drawLine((int)(x), (int)(y), (int) (v.x), (int) (v.y)); 

            //---Draw 3D Walls---
            double ca = theta-ra; if(ca<0) { ca+=2*PI; } if(ca>2*PI) { ca-=2*PI; }; dist*=Math.cos(ca); // fix fisheye
            double lineH = (mapS*320)/dist; if(lineH>320) { lineH=320; }                                // line height
            double lineO = 160-lineH/2;                                                                 // line offset

            g.fillRect(r*8+530, (int)lineO, 8, (int)(lineH));

            // Increment the ray angle to create 60 rays (30 degrees of view on each side)
            ra+=DR; if(ra<0) { ra+=2*PI; } if(ra>2*PI) { ra-=2*PI; };
        }
    }
}

