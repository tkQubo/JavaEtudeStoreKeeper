package com.qubo.challenge.storekeeper.elements;

import java.text.MessageFormat;

/**
 * �Q�[���}�b�v�̈ʒu��ړ��ʂ�\�������N���X
 * @author Qubo
 */
public class Position {
	/**
	 * ��ւ�1�Z�����̈ړ���\��
	 */
	public static final Position UP = new Position(-1, 0);
	/**
	 * ���ւ�1�Z�����̈ړ���\��
	 */
	public static final Position DOWN = new Position(1, 0);
	/**
	 * ���ւ�1�Z�����̈ړ���\��
	 */
	public static final Position LEFT = new Position(0, -1);
	/**
	 * �E�ւ�1�Z�����̈ړ���\��
	 */
	public static final Position RIGHT = new Position(0, 1);

	private final int row;
	private final int col;
	/**
	 * �s�̈ʒu���擾����
	 * @return �s�̈ʒu
	 */
	public int getRow() {
		return row;
	}
	/**
	 * ��̈ʒu���擾����
	 * @return ��̈ʒu
	 */
	public int getCol() {
		return col;
	}

	/**
	 * �W���̃R���X�g���N�^
	 * @param row ��̈ʒu
	 * @param col �s�̈ʒu
	 */
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * �������g�ɁA{@code other}���ړ��ʂƂ��Ēǉ�����{@link Position}�I�u�W�F�N�g���A�V�K�쐬���ĕԂ�
	 * @param other �ړ���
	 * @return {@link Position}�I�u�W�F�N�g
	 */
	public Position add(Position other) {
		return new Position(this.row + other.row, this.col + other.col);
	}
	/**
	 * �������g�Ƌt�̈ړ��ʂ�����{@link Position}�I�u�W�F�N�g���A�V�K�쐬���ĕԂ�
	 * @return {@link Position}�I�u�W�F�N�g
	 */
	public Position reverse() {
		return new Position(-row, -col);
	}

	/*
	 * (�� Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Position) {
			Position other = (Position) obj;
			return this.row == other.row && this.col == other.col;
		}
		return false;
	}
	/*
	 * (�� Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override public String toString() { return MessageFormat.format("[R{0}C{1}]", row, col); }
}
