package com.qubo.challenge.storekeeper.elements;

/**
 * �萔���`�����C���^�[�t�F�[�X
 * @author Qubo
 */
public interface Constants {
	/**
	 * �Q�[���t�@�C�����́A�R�����g�s�̐擪
	 */
	public static final String COMMENT_START = ";";
	/**
	 * �v���[���[��\������
	 */
	public static final char SYMBOL_PLAYER = 'p';
	/**
	 * �ו��u����ɏ�����v���[���[��\������
	 */
	public static final char SYMBOL_PLAYER_ON_DEST = 'P';
	/**
	 * �ו���\������
	 */
	public static final char SYMBOL_STUFF = 'o';
	/**
	 * �ו��u����ɏ�����ו���\�������B�S�Ẳו������̏�ԂɂȂ�΃Q�[���N���A�B
	 */
	public static final char SYMBOL_STUFF_ON_DEST = 'O';
	/**
	 * �ǂ�\�������B�v���[���[���ו����A�Ǐ�Ɉړ������邱�Ƃ͂ł��Ȃ��B
	 */
	public static final char SYMBOL_WALL = '#';
	/**
	 * �󔒒n�т�\�������B�v���[���[�Ɖו������̏�Ɉړ������邱�Ƃ��ł���B
	 */
	public static final char SYMBOL_EMPTY = ' ';
	/**
	 * �ו��u�����\�������B�v���[���[�Ɖו������̏�Ɉړ������邱�Ƃ��ł���B
	 * �S�Ẳו��������ɒu�����Ƃ��ł���΃N���A�B
	 */
	public static final char SYMBOL_DEST = '.';
	/**
	 * ���̑��̕����B�Q�[���t�@�C���ɋ��e����Ȃ������������Ă��鎞�ɂ�����g���B
	 */
	public static final char SYMBOL_ERROR = '?';
	static final String LINE_SEPARATOR = System.getProperty("line.separator");

}
