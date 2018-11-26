package Coords;

import Geom.Geom_element;
import Geom.Point3D;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class coords_converterTest 
{
	@Test
	public void testDistance3d()
	{
		    double x0=32.103315;
		    double y0=35.209039;
		    double z0=670;
		    
		    double x1=32.106352;
		    double y1=35.205225;
		    double z1=650;
		    double ans=0;
		    
		   
			Point3D p0 = new Point3D(x0,y0,z0);
			Point3D p1 = new Point3D(x1,y1,z1);
			//טעות בגלל שזה אובייקט משהו כזה..
			//ans=distance3d(p0,p1);
			double check=493.0523;
			System.out.println(ans);
			assertTrue(ans==check);
	}

	
	
	

}

