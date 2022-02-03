package prime._METATRON.Geom;

import prime._METATRON.Core.Transform;
import prime._METATRON.Core.iTransformController;

public class Mesh implements iTransformController {

	public Transform transform;
	public aGeoset geometry;

	public Mesh() {
		this.transform = new Transform();
		this.geometry = new aGeoset();
	}

	public Mesh(iTransformController parent) {
		this.transform = new Transform();
		this.transform.setParent(parent.getTransform());
	}

	public void clear() {
		this.geometry.clear();
	}

	public void dispose() {
		this.clear();
	}

	@Override
	public Transform getTransform() {
		return this.transform;
	}

}
