package Coords;
import java.io.Serializable;
import Geom.Geom_element;
import Geom.Point3D;
import Coords.coords_converter;
import Coords.MyCoords;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



/**
 * this is the unit test class for the coords_converter functions.
 * in each function we put example and make sure that the function work's good.
 * after you will run this test you will see the green stripe which mean that all the function's work good.
 */

class coords_converterTest 
{
	
	/**
	 * in this function we will check the distance between two points
	 */
	
	@Test
	public void testDistance3d()
	{
		    MyCoords m = new MyCoords();
		    double x0=32.103315;
		    double y0=35.209039;
		    double z0=670;
		    
		    double x1=32.106352;
		    double y1=35.205225;
		    double z1=650;
		    
		    double ans=0;
		    
			Point3D p0 = new Point3D(x0,y0,z0);
			Point3D p1 = new Point3D(x1,y1,z1);
			ans=m.distance3d(p0,p1);
			
			double check=493.05233183241336;
			
			assertTrue(ans==check);
 	}
	
	/**
	 * in this function we will make sure that the function add two point in correct way.
	 */
     
	@Test
	
	public void addToPoint()
	{
		MyCoords m = new MyCoords();
	    double x0=23;
	    double y0=43;
	    double z0=54;
	    
	    double x1=32.106352;
	    double y1=35.205225;
	    double z1=65;
	    
	    double x2=0;
	    double y2=0;
	    double z2=0;
	    
		Point3D p0 = new Point3D(x0,y0,z0);
		Point3D p1 = new Point3D(x1,y1,z1);
		
		Point3D ans = new Point3D(x2,y2,z2);
		
		ans=m.add(p1, p0);
		assertTrue(ans.x()==104.86623105432498 && ans.y()==77.28886351345534 && ans.z()==126.8583987677383);
	}
	
	/**
	 * in this function we will return the distance vector between two point's.
	 */
	
	@Test
	
	public void vector3DTest()
	{
		MyCoords m = new MyCoords();
		double x0=32.103315;
	    double y0=35.209039;
	    double z0=670;
	    
	    double x1=32.106352;
	    double y1=35.205225;
	    double z1=650;
	    
	    double x=0,y=0,z=0;
	    
		Point3D p0 = new Point3D(x0,y0,z0);
		Point3D p1 = new Point3D(x1,y1,z1);
		
		Point3D ans = new Point3D(x,y,z);
		ans=m.vector3D(p0,p1);
		assertTrue(ans.x()==337.69899206128815 && ans.y()==-359.2492069388189 && ans.z()==-20);
	}
	
	/**
	 * in this function we will return the array that will contain the azimuth,elevation and the distance
	 * between two points.
	 */
	
	@Test 
	
	public void azimuth_elevation_distTest()
	{
		MyCoords m = new MyCoords();
		
		double x0=100;
		double y0=170;
		double z0=0;
		
		double x1=150;
		double y1=120;
		double z1=0;
		
		double azimuth=135;
		double elevation=34;
		double dist=118.25;
		
		double[] ans= {0,0,0}; 
		
		Point3D p0 = new Point3D(x0,y0,z0);
		Point3D p1 = new Point3D(x1,y1,z1);
		
		ans=m.azimuth_elevation_dist(p0, p1);
		assertTrue(ans[0]==azimuth);
		
	}
	
	/**
	 * in this function we will check if a given point is legal or not.
	 * we will return true if the point is legal, and false if the point is not legal.
	 */
	
	@Test
	
	public void isValid_GPS_Point_Test()
	{
		MyCoords m = new MyCoords();
		
		double x0=80;
		double y0=100;
		double z0=0;
		
		double x1=10;
		double y1=120;
		double z1=0;
		
		boolean ans0 =false;
		boolean ans1 =false;
		
		Point3D p0 = new Point3D(x0,y0,z0);
		Point3D p1 = new Point3D(x1,y1,z1);
		
		ans0=m.isValid_GPS_Point(p0);
		ans1=m.isValid_GPS_Point(p1);
		
		
		assertTrue(ans0);
		assertTrue(ans1);
		
		
	}

}

