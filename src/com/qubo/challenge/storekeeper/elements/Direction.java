package com.qubo.challenge.storekeeper.elements;

/**
 * �㉺���E�̐i�s������\���񋓌^
 * @author Qubo
 */
public enum Direction {
	/**
	 * ��
	 */
	Up { @Override Position toPosition() { return Position.UP; } },
	/**
	 * ��
	 */
	Down { @Override Position toPosition() { return Position.DOWN; } },
	/**
	 * �E
	 */
	Right { @Override Position toPosition() { return Position.RIGHT; } },
	/**
	 * ��
	 */
	Left { @Override Position toPosition() { return Position.LEFT; } },
	;
	/**
	 * �i�s�����ւ�1�Z�����̈ړ���\��{@link Position}�I�u�W�F�N�g��Ԃ��B
	 * @return
	 */
	abstract Position toPosition();
}
