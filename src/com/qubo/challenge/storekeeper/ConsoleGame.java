package com.qubo.challenge.storekeeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;

import com.qubo.challenge.storekeeper.elements.Direction;
import com.qubo.challenge.storekeeper.elements.StoreKeeper;

/**
 * �R���\�[���v���O�����Ƃ��đq�ɔԂ��v���C���邽�߂̃N���X
 * @author Qubo
 */
public class ConsoleGame {
	/**
	 * �R���\�[�����͂̃L�����Z�������i�H�j
	 */
	private static final char COMMAND_CANCEL = '!';
	/**
	 * �R���\�[�����́F�u��Ɉړ��v
	 */
	public static final char COMMAND_MOVE_UP = 'w';
	/**
	 * �R���\�[�����́F�u���Ɉړ��v
	 */
	public static final char COMMAND_MOVE_DOWN = 's';
	/**
	 * �R���\�[�����́F�u���Ɉړ��v
	 */
	public static final char COMMAND_MOVE_LEFT = 'a';
	/**
	 * �R���\�[�����́F�u�E�Ɉړ��v
	 */
	public static final char COMMAND_MOVE_RIGHT = 'd';
	/**
	 * �R���\�[�����́F�u��蒼���v
	 */
	public static final char COMMAND_UNDO = 'u';
	/**
	 * �R���\�[�����́F�u���Z�b�g�v
	 */
	public static final char COMMAND_RESET = '@';
	/**
	 * �R���\�[�����́F�u���f�v
	 */
	public static final char COMMAND_QUIT = '_';
	/**
	 * �㉺���E�̈ړ��n�R���\�[�����͂𕶎���Ƃ��ĘA����������
	 */
	private static final String COMMAND_MOVES = "" + COMMAND_MOVE_UP + COMMAND_MOVE_DOWN + COMMAND_MOVE_LEFT + COMMAND_MOVE_RIGHT;

	/**
	 * �q�ɔԂ��v���C����
	 * @param path �Q�[���t�@�C���̃p�X
	 * @throws IOException
	 */
	public void play(String path) throws IOException {
		showSplash();

		Loader loader = new Loader();
		StoreKeeper storeKeeper = loader.load(path);
		storeKeeper.reset();

		render(storeKeeper);

		do {
			char command = requestCommand();
			if (command == COMMAND_QUIT) {
				break;
			} else if (command == COMMAND_RESET) {
				storeKeeper.reset();
			} else if (command == COMMAND_UNDO) {
				storeKeeper.undo();
			} else if (COMMAND_MOVES.contains("" + command)) {
				storeKeeper.move(toDirection(command));
			}

			render(storeKeeper);

		} while (!storeKeeper.isCleared());

		showResult(storeKeeper);
	}

	/**
	 * �Q�[���N����ʂ�\������
	 */
	private void showSplash() {
		System.out.println("�Q�Q�Q�Q�������Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q���Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�����Q�Q");
		System.out.println("�Q�Q�Q���Q�Q�Q���Q�Q�Q�Q�Q���������������������Q�Q�Q�����������Q���Q�Q");
		System.out.println("�Q�Q���Q�������Q���Q�Q�Q�Q���Q�Q�Q�Q���Q�Q�Q�Q�Q�Q�Q�Q���Q���Q���Q�Q�Q");
		System.out.println("�����Q�Q�Q�Q�Q�Q�Q�����Q�Q���������������������Q����������������������");
		System.out.println("�Q�Q���������������Q�Q�Q�Q���Q���Q�Q���Q�Q���Q�Q�Q�Q�Q���Q���Q���Q�Q�Q");
		System.out.println("�Q�Q���Q�Q�Q�Q�Q���Q�Q�Q�Q���Q���������������Q�Q�Q�Q���Q�Q���Q�Q���Q�Q");
		System.out.println("�Q�Q���������������Q�Q�Q�Q���Q���Q�Q���Q�Q���Q�Q����������������������");
		System.out.println("�Q�Q���Q�Q�Q�Q�Q�Q�Q�Q�Q�Q���Q���������������Q�Q�Q�Q���Q�Q���Q�Q���Q�Q");
		System.out.println("�Q���Q�������������Q�Q�Q�Q���Q�Q�Q�Q���Q�Q�Q�Q�Q�Q�Q���������������Q�Q");
		System.out.println("���Q�Q���Q�Q�Q�Q���Q�Q�Q���Q�������������������Q�Q�Q���Q�Q���Q�Q���Q�Q");
		System.out.println("�Q�Q�Q�������������Q�Q�Q���Q�Q�Q�Q�Q���Q�Q�Q�Q�Q�Q�Q���������������Q�Q");
		System.out.println("�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q");
		System.out.println("Please Enter Key... Game Start");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try { reader.readLine(); } catch (IOException e) { e.printStackTrace(); }
	}

	/**
	 * �Q�[���I����̌��ʂ�\������
	 * @param storeKeeper
	 */
	private void showResult(StoreKeeper storeKeeper) {
		String message = storeKeeper.isCleared() ? "�Q�[���N���A�I���߂łƂ��I�I" : "�Q�[���𒆒f���܂����I";
		System.out.println(message);
	}
	/**
	 * �Q�[����ʂ�\������
	 * @param storeKeeper
	 */
	private void render(StoreKeeper storeKeeper) {
		System.out.println(storeKeeper);
		System.out.println(MessageFormat.format("�ړ���: {0}", storeKeeper.getStep()));
	}
	/**
	 * �㉺���E�̈ړ��n�̃R���\�[�����͂��A{@link Direction}�I�u�W�F�N�g�ɕϊ�����<br>
	 * ���͂��ړ��n�ł͂Ȃ��ꍇ�́Anull��Ԃ�
	 * @param command �R���\�[������
	 * @return {@link Direction}�I�u�W�F�N�g
	 */
	private Direction toDirection(char command) {
		switch (command) {
		case COMMAND_MOVE_UP: return Direction.Up;
		case COMMAND_MOVE_DOWN: return Direction.Down;
		case COMMAND_MOVE_LEFT: return Direction.Left;
		case COMMAND_MOVE_RIGHT: return Direction.Right;
		default: return null;
		}
	}
	/**
	 * �R���\�[������ꕶ���擾����B�L�����Z������({@link ConsoleGame#COMMAND_CANCEL})���܂܂�Ă���ꍇ��-1��Ԃ��B
	 * @return �R�}���h����
	 * @throws IOException
	 */
	private char requestCommand() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(
				"�ړ�: (��->w, ��->a, ��->s, �E->d) + Enter"
				+ "\r\n"
				+ "�߂�->u, ���Z�b�g->@, ���f->_, ���̓L�����Z��->!���܂߂�");
		String line = reader.readLine();
		if (!line.contains("" + COMMAND_CANCEL) && line.length() > 0) {
			return line.charAt(0);
		} else {
			return (char) -1;
		}
	}
}
