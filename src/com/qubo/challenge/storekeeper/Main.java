package com.qubo.challenge.storekeeper;

import java.io.IOException;

/**
 * �R���\�[���v���O�����̃G���g���|�C���g����`���ꂽ�N���X
 * @author Qubo
 */
public class Main {
	/**
	 * �T���v���p�Q�[���t�@�C��
	 */
	private static final String GAMEMAP1 = "gamemap_1.txt";
	/**
	 * �G���g���|�C���g�B
	 * 1�Ԗڂ̈������Q�[���t�@�C�����Ƃ��āA{@link ConsoleGame#play(String)}�����s����B
	 * �������^�����Ă��Ȃ��ꍇ�́A�f�t�H���g��{@link Main#GAMEMAP1}���Q�[���t�@�C�����Ƃ���B
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String path = args.length > 0 ? args[0] : GAMEMAP1;
			ConsoleGame game = new ConsoleGame();
			game.play(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
