package assets.elementalpets.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelElementalWater extends ModelBase {
	    public ModelRenderer Body;
	    public ModelRenderer Lower_LArm;
	    public ModelRenderer Lower_RArm;
	    public ModelRenderer Upper_LArm;
	    public ModelRenderer Upper_RArm;
	    public ModelRenderer Lower_LArm02;
	    public ModelRenderer Lower_RArm02;
	    public ModelRenderer Upper_LArm02;
	    public ModelRenderer Upper_RArm02;

	    public ModelElementalWater() {
	        this.textureWidth = 64;
	        this.textureHeight = 32;
	        this.Lower_LArm = new ModelRenderer(this, 0, 22);
	        this.Lower_LArm.setRotationPoint(2.0F, 4.5F, 0.0F);
	        this.Lower_LArm.addBox(-1.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
	        this.Body = new ModelRenderer(this, 0, 0);
	        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
	        this.Body.addBox(-5.0F, -5.0F, -5.0F, 10, 10, 10, 0.0F);
	        this.Lower_LArm02 = new ModelRenderer(this, 16, 21);
	        this.Lower_LArm02.setRotationPoint(1.0F, 5.5F, 0.0F);
	        this.Lower_LArm02.addBox(-1.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F);
	        this.Lower_RArm02 = new ModelRenderer(this, 16, 21);
	        this.Lower_RArm02.setRotationPoint(0.0F, 5.5F, 0.0F);
	        this.Lower_RArm02.addBox(-1.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F);
	        this.Upper_RArm02 = new ModelRenderer(this, 16, 21);
	        this.Upper_RArm02.setRotationPoint(0.0F, -8.5F, 0.0F);
	        this.Upper_RArm02.addBox(-1.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F);
	        this.Upper_RArm = new ModelRenderer(this, 0, 22);
	        this.Upper_RArm.setRotationPoint(-3.0F, -11.0F, 0.0F);
	        this.Upper_RArm.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
	        this.Lower_RArm = new ModelRenderer(this, 0, 22);
	        this.Lower_RArm.setRotationPoint(-3.0F, 4.5F, 0.0F);
	        this.Lower_RArm.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
	        this.Upper_LArm = new ModelRenderer(this, 0, 22);
	        this.Upper_LArm.setRotationPoint(3.0F, -11.0F, 0.0F);
	        this.Upper_LArm.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
	        this.Upper_LArm02 = new ModelRenderer(this, 16, 21);
	        this.Upper_LArm02.setRotationPoint(0.0F, -8.5F, 0.0F);
	        this.Upper_LArm02.addBox(-1.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F);
	        this.Body.addChild(this.Lower_LArm);
	        this.Lower_LArm.addChild(this.Lower_LArm02);
	        this.Lower_RArm.addChild(this.Lower_RArm02);
	        this.Upper_RArm.addChild(this.Upper_RArm02);
	        this.Body.addChild(this.Upper_RArm);
	        this.Body.addChild(this.Lower_RArm);
	        this.Body.addChild(this.Upper_LArm);
	        this.Upper_LArm.addChild(this.Upper_LArm02);
	    }

	    @Override
	    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
	        this.Body.render(f5);
	    }

	    /**
	     * This is a helper function from Tabula to set the rotation of model parts
	     */
	    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
	        modelRenderer.rotateAngleX = x;
	        modelRenderer.rotateAngleY = y;
	        modelRenderer.rotateAngleZ = z;
	    }
}
