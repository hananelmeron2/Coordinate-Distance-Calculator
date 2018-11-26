package Coords;

import java.io.Serializable;

import Geom.Geom_element;
import Geom.Point3D;


public class MyCoords implements coords_converter
{

	public  void  convertCarToPol(Point3D p)
	{
		double r;//מייצגת את המרחק שבין הנקודה לראשית הצירים, מקבלת ערך ממשי לא שלילי
		double teta;//קו רוחב
	    double pi;//קו אורך
    	    
	    r=Math.sqrt(p.x()*p.x()+p.y()*p.y()+p.z()*p.z());
	    teta=Math.acos(p.x()/r);
	    pi=Math.atan(p.y()/p.x());
	    
	}
	
	
	//in this function we need to get vector gps, and vector in metrers, convert the gps vector to meters, unit them, and convert again
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter)//פשוט להמיר את הווקטור של המטרים לפולרי ולחבר
	{
		convertCarToPol(local_vector_in_meter);
		double x,y,z;
		x=gps.x()+local_vector_in_meter.x();
		y=gps.y()+local_vector_in_meter.y();
	    z=gps.z()+local_vector_in_meter.z();
		Point3D p = new Point3D(x,y,z);
		
		return p;
	}
	
	//לפי האקסל
	@Override
	public double distance3d(Point3D gps0, Point3D gps1)
	{	
		double diff0,diff1,diff2;
		double diffGps0Radian,diffGps1Radian;
		double gps0InMeters,gps1InMeters;
		double radios=6371*1000;//רדיוס כדור הארץ, לא הבנתי למה כפול 1000
		double lonNorm;
		double ans;
		diff0=gps0.x()-gps1.x();//x0-x1
		diff1=gps0.y()-gps1.y();//y0-y1
		diff2=gps0.z()-gps1.z();//z0-z1//בזה לא משתמשים , צריך לראות למה
		lonNorm=Math.cos(gps0.x()*(Math.PI/180));
		diffGps0Radian=diff0*(Math.PI/180);
		diffGps1Radian=diff1*(Math.PI/180);
		gps0InMeters=radios*Math.sin(diffGps0Radian);
		gps1InMeters=radios*lonNorm*Math.sin(diffGps1Radian);
		ans=Math.sqrt((gps0InMeters*gps0InMeters)+(gps1InMeters*gps1InMeters));
		
		return ans;
	}
	
	
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1)//להחזיר מהאקסל את ההווקטור
	{
		double diff0,diff1,diff2;
		double diffGps0Radian,diffGps1Radian;
		double gps0InMeters,gps1InMeters;
		double radios=6371*1000;//רדיוס כדור הארץ, לא הבנתי למה כפול 1000
		double lonNorm;
		double ans;
		diff0=gps0.x()-gps1.x();//x0-x1
		diff1=gps0.y()-gps1.y();//y0-y1
		diff2=gps0.z()-gps1.z();//z0-z1//בזה לא משתמשים , צריך לראות למה
		lonNorm=Math.cos(gps0.x()*(Math.PI/180));
		diffGps0Radian=diff0*(Math.PI/180);
		diffGps1Radian=diff1*(Math.PI/180);
		gps0InMeters=radios*Math.sin(diffGps0Radian);
		gps1InMeters=radios*lonNorm*Math.sin(diffGps1Radian);
		//ans=Math.sqrt((gps0InMeters*gps0InMeters)+(gps1InMeters*gps1InMeters));
		Point3D ans1= new Point3D(gps0InMeters,gps1InMeters,diff2);
		
		return ans1;
	}
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1)
	{
		//בגדול הפונקציה גמורה , יש קצת הבדל באלווישן שלא הבנתי למה יש מהאתר עצמו, ביוניטטסט נריץ ונבדוק
        double azimuth = 0;
        double deltaX,deltaY;
        double convert;
        double angle;
        double elevation;
        double x=0,y=0,z=0;
        double x1=0,y1=0,z1=0;
        double dist;
        double[] ans= {0,0,0}; 
        deltaX=gps1.x()-gps0.x();
        deltaY=gps1.y()-gps1.y();
        convert=Math.abs(deltaY/deltaX);
        angle=Math.atan(convert);
        if(deltaX>=0 && deltaY>=0)
        {
        	azimuth=angle;
        }
        if(deltaX<0 && deltaY>=0)
        {
        	azimuth=180-angle;
        }
        if(deltaX<0 && deltaY<0)
        {
        	azimuth=angle+180;
        }
        if(deltaX>=0 && deltaY<0)
        {
        	azimuth=360-angle;
        }
        
         ans[0]=azimuth;
         
         
        //Cos(elevation) = (x*dx + y*dy + z*dz) / Sqrt((x^2+y^2+z^2)*(dx^2+dy^2+dz^2))
         
        x1=gps1.x()-gps0.x();
        y1=gps1.y()-gps0.y();
        z1=gps1.z()-gps0.z();
        Point3D gps2 = new Point3D(x1,y1,z1);
        
        elevation=Math.cos((gps0.x()*gps2.x()+gps0.y()*gps2.y()+gps0.z()*gps2.z())/Math.sqrt((gps0.x()*gps0.x()+gps0.y()*gps0.y()+gps0.z()*gps0.z())*((gps2.x()*gps2.x()+gps2.y()*gps2.y()+gps2.z()*gps2.z()))));
        ans[1]=elevation;
        
        //dist? between the two points??
        dist=distance3d(gps0,gps1);
        ans[2]=dist;
        
        
        
		   return null;
	}
	@Override
	public boolean isValid_GPS_Point(Point3D p)
	{
		//עשיתי לפי מה שרשמו בקבוצה מקווה שנכון
		if(p.x()>=-90 && p.x()<=90 && p.y()>=-180 && p.y()<=180)
		{
			return true;
		}
		
		return false;
	}
	
}
