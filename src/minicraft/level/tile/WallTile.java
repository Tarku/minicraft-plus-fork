package minicraft.level.tile;

import java.util.EnumMap;
import minicraft.entity.Entity;
import minicraft.entity.Mob;
import minicraft.entity.Player;
import minicraft.entity.particle.SmashParticle;
import minicraft.entity.particle.TextParticle;
import minicraft.gfx.Color;
import minicraft.gfx.ConnectorSprite;
import minicraft.gfx.Sprite;
import minicraft.item.Item;
import minicraft.item.Items;
import minicraft.item.ToolItem;
import minicraft.item.ToolType;
import minicraft.level.Level;
import minicraft.screen.ModeMenu;

public class WallTile extends Tile {
	private ConnectorSprite sprite;
	
	protected Material type;
	
	/*private static EnumMap<Material, ConnectorSprite> spriteMap = new EnumMap<Material, ConnectorSprite> {{
		put(Material.Wood, new ConnectorSprite(WallTile.class, new Sprite(4, 22, 3, 3, Color.get(100, 430, 320, 540), 3), new Sprite(7, 22, 2, 2, Color.get(100, 430, 320, 540), 3), new Sprite(5, 23, 2, 2, Color.get(430, 430, 320, 320), 0, true)));
		put(Material.Stone, new ConnectorSprite(WallTile.class, new Sprite(4, 25, 3, 3, Color.get(111, 333, 444, 444), 3), new Sprite(7, 24, 2, 2, Color.get(111, 444), 3), Sprite.blank(2, 2, 444)));
		put(Material.Obsidian, new ConnectorSprite(WallTile.class, new Sprite(4, 25, 3, 3, Color.get(000, 203, 103, 103), 3), new Sprite(7, 24, 2, 2, Color.get(000, 103), 3), Sprite.blank(2, 2, 223)));
	}};*/
	
	protected WallTile(Material type) {
		super(type.name()+" Wall", (ConnectorSprite)null);
		switch(type) {
			case Wood: sprite = new ConnectorSprite(WallTile.class, new Sprite(4, 22, 3, 3, Color.get(100, 430, 320, 540), 3), new Sprite(7, 22, 2, 2, Color.get(100, 430, 320, 540), 3), new Sprite(5, 23, 2, 2, Color.get(430, 430, 320, 320), 0, true));
			break;
			case Stone: sprite = new ConnectorSprite(WallTile.class, new Sprite(4, 25, 3, 3, Color.get(111, 333, 444, 444), 3), new Sprite(7, 24, 2, 2, Color.get(111, 444), 3), Sprite.blank(2, 2, 444));
			break;
			case Obsidian: sprite = new ConnectorSprite(WallTile.class, new Sprite(4, 25, 3, 3, Color.get(000, 203, 103, 103), 3), new Sprite(7, 24, 2, 2, Color.get(000, 103), 3), Sprite.blank(2, 2, 223));
			break;
		}
		csprite = sprite;
	}
	
	//public void render(Screen screen, Level level, int x, int y) {
		//byte data = level.getData(x, y);
		
		
		/*int col = Color.get(444, 444);
		int col1 = Color.get(111, 333, 444, 444);
		int col2 = Color.get(111, 444);
		
		int transitionColor = col1;
		int backColor = col2;
		
		boolean u = level.getTile(x, y - 1) != this;
		boolean d = level.getTile(x, y + 1) != this;
		boolean l = level.getTile(x - 1, y) != this;
		boolean r = level.getTile(x + 1, y) != this;

		boolean ul = level.getTile(x - 1, y - 1) != this;
		boolean dl = level.getTile(x - 1, y + 1) != this;
		boolean ur = level.getTile(x + 1, y - 1) != this;
		boolean dr = level.getTile(x + 1, y + 1) != this;

		if (!u && !l) {
			if (!ul) screen.render(x * 16 + 0, y * 16 + 0, 7 + 2 * 32, col, 0);
			else screen.render(x * 16 + 0, y * 16 + 0, 7 + 24 * 32, backColor, 3);
		} else
			screen.render(
					x * 16 + 0, y * 16 + 0, (l ? 6 : 5) + (u ? 27 : 26) * 32, transitionColor, 3);

		if (!u && !r) {
			if (!ur) screen.render(x * 16 + 8, y * 16 + 0, 7 + 2 * 32, col, 0);
			else screen.render(x * 16 + 8, y * 16 + 0, 8 + 24 * 32, backColor, 3);
		} else
			screen.render(
					x * 16 + 8, y * 16 + 0, (r ? 4 : 5) + (u ? 27 : 26) * 32, transitionColor, 3);

		if (!d && !l) {
			if (!dl) screen.render(x * 16 + 0, y * 16 + 8, 7 + 2 * 32, col, 0);
			else screen.render(x * 16 + 0, y * 16 + 8, 7 + 25 * 32, backColor, 3);
		} else
			screen.render(
					x * 16 + 0, y * 16 + 8, (l ? 6 : 5) + (d ? 25 : 26) * 32, transitionColor, 3);
		if (!d && !r) {
			if (!dr) screen.render(x * 16 + 8, y * 16 + 8, 7 + 2 * 32, col, 0);
			else screen.render(x * 16 + 8, y * 16 + 8, 8 + 25 * 32, backColor, 3);
		} else
			screen.render(
					x * 16 + 8, y * 16 + 8, (r ? 4 : 5) + (d ? 25 : 26) * 32, transitionColor, 3);
		*/
	//}

	public boolean mayPass(Level level, int x, int y, Entity e) {
		return false;
	}

	public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
		int playDmg;
		if (ModeMenu.creative) playDmg = random.nextInt(5);
		else {
			playDmg = 0;
		}
		hurt(level, x, y, playDmg);
	}

	public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
		if (item instanceof ToolItem) {
			ToolItem tool = (ToolItem) item;
			if (tool.type == ToolType.Pickaxe) {
				if (player.payStamina(4 - tool.level)) {
					hurt(level, xt, yt, random.nextInt(10) + (tool.level) * 5 + 10);
					return true;
				}
			}
		}
		/*if (item instanceof ToolItem) {
			ToolItem tool = (ToolItem) item;
			if (tool.type == ToolType.pick) {
				if (player.payStamina(4 - tool.level)) {
					hurt(level, xt, yt, random.nextInt(6) + (tool.level) * 5 + 5);
					return true;
				}
			}
		}*/
		return false;
	}

	public void hurt(Level level, int x, int y, int dmg) {
		int damage = level.getData(x, y) + dmg;
		int sbwHealth;
		if (ModeMenu.creative) sbwHealth = 1;
		else {
			sbwHealth = 100;
		}
		level.add(new SmashParticle(x * 16 + 8, y * 16 + 8));
		level.add(new TextParticle("" + dmg, x * 16 + 8, y * 16 + 8, Color.get(-1, 500)));
		if (damage >= sbwHealth) {
			String itemName = "", tilename = "";
			switch(type) {
				case Wood: itemName = "Plank"; tilename = "Wood Planks"; break;
				case Stone: itemName = "Stone Brick"; tilename = "Stone Bricks"; break;
				case Obsidian: itemName = "Obsidian Brick"; tilename = "Obsidian"; break;
			}
			
			level.dropItem(x*16, y*16, 1, 3-type.ordinal(), Items.get(itemName));
			level.setTile(x, y, Tiles.get(tilename)); // TODO this will be a problem...
		}
		else {
			level.setData(x, y, damage);
		}
	}

	public void tick(Level level, int xt, int yt) {
		int damage = level.getData(xt, yt);
		if (damage > 0) level.setData(xt, yt, damage - 1);
	}
	
	public String getName(int data) {
		return Material.values[data].name() + " Wall";
	}
}
