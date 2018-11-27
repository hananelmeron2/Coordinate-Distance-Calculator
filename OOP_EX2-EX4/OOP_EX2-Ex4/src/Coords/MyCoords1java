package Coords;

import java.io.Serializable;
import java.text.DecimalFormat;

import Geom.Geom_element;
import Geom.Point3D;


/**
 * this class will implement the interface coords_converter, the class coords_converter will do basic coordinate system converter
 * all the functions in the interface are here.
 */


public class MyCoords implements coords_converter
{
	
	/**
	 * this function will help us in the add function (we will talk about this function later), in this function we will 
	 * take coordinate that represent point in the space(vector) in Cartesian , and we will transformed the point to polar
	 * presentation.
	 * @param   Point3D p
	 * @return new point p0
	 */

	public Point3D convertCarToPol(Point3D p)
	{
		double r;
		double teta;
	    double pi;
	    
	    r=Math.sqrt(p.x()*p.x()+p.y()*p.y()+p.z()*p.z());
	    teta=(Math.acos(p.z()/r)*180)/Math.PI;
	    pi=(Math.atan(p.y()/p.x())*180)/Math.PI;
	    
	    float n = 5.2f;//we need to check how to do this operation on all the numbers
	    Point3D p0 = new Point3D(r,teta,pi);
	   
	    return p0;
	    
	}
	
	
	/**
	 * in this function we will add  point that have polar presentation , to another point that have Cartesian presentation.
	 * now the function convertCarToPol will help us to convert the point presentation from Cartesian to polar,
	 * and now we just sum the point together.
	 * @param Point3D gps
	 * @param Point3D local_vector_in_meter
	 * @return Point3D p
	 * 
	 */
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter)
	{
		double x1=0;
		double y1=0;
		double z1=0;
		
		Point3D p0 = new Point3D(x1,y1,z1);
		
		p0=convertCarToPol(local_vector_in_meter);
		double x,y,z;
		x=gps.x()+p0.x();
		y=gps.y()+p0.y();
	    z=gps.z()+p0.z();
		Point3D p = new Point3D(x,y,z);
		
		return p;
	}
	
	
	/**
	 * this function will get to points and find the distance between them.
	 * we took the way of solution from the excel file.
	 * the function will return ans- the distance between the points.
	 * @param  Point3D gps0
	 * @param  Point3D gps1
	 * @return ans 
	 */
	
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) 
	{	
		double diff0,diff1,diff2;
		double diffGps0Radian,diffGps1Radian;
		double gps0InMeters,gps1InMeters;
		double radios=6371*1000;
		double lonNorm;
		double ans;
		diff0=gps0.x()-gps1.x();//x0-x1
		diff1=gps0.y()-gps1.y();//y0-y1
		diff2=gps0.z()-gps1.z();//z0-z1
		lonNorm=Math.cos(gps0.x()*(Math.PI/180));
		diffGps0Radian=diff0*(Math.PI/180);
		diffGps1Radian=diff1*(Math.PI/180);
		gps0InMeters=radios*Math.sin(diffGps0Radian);
		gps1InMeters=radios*lonNorm*Math.sin(diffGps1Radian);
		ans=Math.sqrt((gps0InMeters*gps0InMeters)+(gps1InMeters*gps1InMeters));
		
		return ans;
	}
	
	
	/**
	 * in this function we will get two points and find the vector between both of them.
	 * this answer is one step before the final answer to the last function.
	 * like the function before the calculation come from the excel .
	 * @param Point3D gps0
	 * @param Point3D gps1
	 * @return Point3D ans1
	 */
	
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1)
	{
		double diff0,diff1,diff2;
		double diffGps0Radian,diffGps1Radian;
		double gps0InMeters,gps1InMeters;
		double radios=6371*1000;
		double lonNorm;
		diff0=gps1.x()-gps0.x();//x0-x1
		diff1=gps1.y()-gps0.y();//y0-y1
		diff2=gps1.z()-gps0.z();//z0-z1
		lonNorm=Math.cos(gps0.x()*(Math.PI/180));
		diffGps0Radian=diff0*(Math.PI/180);
		diffGps1Radian=diff1*(Math.PI/180);
		gps0InMeters=radios*Math.sin(diffGps0Radian);
		gps1InMeters=radios*lonNorm*Math.sin(diffGps1Radian);
		//ans=Math.sqrt((gps0InMeters*gps0InMeters)+(gps1InMeters*gps1InMeters));
		Point3D ans1 = new Point3D(gps0InMeters,gps1InMeters,diff2);
		
		return ans1;
	}
	
	
	/**
	 * in this function we will return array that contain the  azimuth, elevation and distance between two points.
	 * The azimuth is the angle formed between a reference direction,
	 * and a line from the observer to a point of interest projected on the same plane as the reference direction orthogonal to the zenith.
	 * The elevation of a geographic location is its height above or below a fixed reference point.
	 * @param Point3D gps0
	 * @param Point3D gps1
	 * @return ans (array, that contain the answer's).
	 */
	
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1)
	{
        double azimuth = 0;
        double deltaX,deltaY;
        double convert;
        double angle;
        double elevation;
        double x1=0,y1=0,z1=0;
        double dist;
        double[] ans= {0,0,0}; 
        deltaY=gps1.x()-gps0.x();
        deltaX=gps1.y()-gps0.y();
        convert=Math.abs(deltaY/deltaX);
        angle=Math.atan(convert);
        angle=(angle*180)/Math.PI;
        
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
            
        x1=gps1.x()-gps0.x();
        y1=gps1.y()-gps0.y();
        z1=gps1.z()-gps0.z();
        Point3D gps2 = new Point3D(x1,y1,z1);
        
        elevation=Math.cos((gps0.x()*gps2.x()+gps0.y()*gps2.y()+gps0.z()*gps2.z())/Math.sqrt((gps0.x()*gps0.x()+gps0.y()*gps0.y()+gps0.z()*gps0.z())*((gps2.x()*gps2.x()+gps2.y()*gps2.y()+gps2.z()*gps2.z()))));
        ans[1]=elevation;
        
        dist=distance3d(gps0,gps1);
        ans[2]=dist;
        
        
		   return ans;
	}
	
	
	/**
	 * in this function we will make sure that a given point is legal point , we will check it by the angle size of both x any y value's.
	 * @param Point3D p.
	 * @return true - if the point is legal.
	 * @return false - if the point isn't legal.
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p)
	{
		if(p.x()>=-90 && p.x()<=90 && p.y()>=-180 && p.y()<=180)
		{
			return true;
		}
		
		return false;
	}
	
}
