package com.qubo.challenge.storekeeper.elements;

/**
 * �Q�[���}�b�v��̃Z����\�������N���X
 * @author Qubo
 */
public class Cell {
	private FieldObject object;
	private final Terrain terrain;
	private final FieldObject originalObject;
	private final Position position;

	/**
	 * �Z����{@link Terrain}�I�u�W�F�N�g���擾����
	 * @return �Z����{@link Terrain}�I�u�W�F�N�g
	 */
	public Terrain getTerrain() { return terrain; }
	/**
	 * �Z���̌��݂�{@link FieldObject}�I�u�W�F�N�g���擾����
	 * @return �Z���̌��݂�{@link FieldObject}�I�u�W�F�N�g
	 */
	public FieldObject getObject() { return object; }
	/**
	 * �Z���̏�����Ԃł�{@link FieldObject}�I�u�W�F�N�g���擾����
	 * @return �Z���̏�����Ԃł�{@link FieldObject}�I�u�W�F�N�g
	 */
	public FieldObject getOriginalObject() { return originalObject; }
	/**
	 * �Z���̈ʒu���擾����
	 * @return position
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * �Z���̌��݂�{@link FieldObject}�I�u�W�F�N�g��ݒ肷��
	 * @param object
	 */
	void setObject(FieldObject object) { this.object = object; }

	/**
	 * �W���̃R���X�g���N�^
	 * @param field �Z���̒n�`
	 * @param object �Z����̃I�u�W�F�N�g
	 * @param position �Z���̈ʒu
	 */
	public Cell(Terrain field, FieldObject object, Position position) {
		this.terrain = field;
		this.object = object;
		this.originalObject = object;
		this.position = position;
	}

	/**
	 * �Z���̌��݂�{@link FieldObject}�I�u�W�F�N�g��������Ԃɖ߂�
	 */
	void reset() { this.object = this.originalObject; }
	/**
	 * �Z���̏�Ԃ��A�����\���Ƃ��Ď擾����
	 * @return �����\���ɂ��Z���̏��
	 */
	public char toChar() {
		char ch;
		if (terrain == Terrain.Wall) {
			ch = Constants.SYMBOL_WALL;
		} else if (terrain == Terrain.Dest) {
			if (object == FieldObject.Player) {
				ch = Constants.SYMBOL_PLAYER_ON_DEST;
			} else if (object == FieldObject.Stuff) {
				ch = Constants.SYMBOL_STUFF_ON_DEST;
			} else {
				ch = Constants.SYMBOL_DEST;
			}
		} else if (terrain == null) {
			if (object == FieldObject.Player) {
				ch = Constants.SYMBOL_PLAYER;
			} else if (object == FieldObject.Stuff) {
				ch = Constants.SYMBOL_STUFF;
			} else {
				ch = Constants.SYMBOL_EMPTY;
			}
		} else {
			ch = Constants.SYMBOL_ERROR;
		}
		return ch;
	}

	/*
	 * (�� Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override public String toString() { return "" + toChar(); }
}
