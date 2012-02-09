package com.qubo.challenge.storekeeper.elements;

import java.text.MessageFormat;

/**
 * �v���[���[�́u�ړ��v��\�������N���X
 * @author Qubo
 */
class Move {
	private final boolean shouldPushStuff;
	private final Position delta;

	/**
	 * �ړ��ʂ�\����{@link Position}�I�u�W�F�N�g���擾����
	 * @return
	 */
	Position getDelta() {
		return delta;
	}
	/**
	 * �ړ����ɉו��𓮂����Ă��邩�ǂ������擾����
	 * @return
	 */
	boolean shouldPushStuff() {
		return shouldPushStuff;
	}

	/**
	 * �W���̃R���X�g���N�^
	 * @param delta �ړ���
	 * @param shouldPushStuff �ו��𓮂����Ă��邩�ǂ���
	 */
	Move(Position delta, boolean shouldPushStuff) {
		this.delta = delta;
		this.shouldPushStuff = shouldPushStuff;
	}

	/*
	 * (�� Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return MessageFormat.format("move({0}{1})", delta, shouldPushStuff ? ", PUSH" : "");
	}
}
