package com.qubo.challenge.storekeeper;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.qubo.Utils;
import com.qubo.challenge.storekeeper.elements.Cell;
import com.qubo.challenge.storekeeper.elements.Constants;
import com.qubo.challenge.storekeeper.elements.FieldObject;
import com.qubo.challenge.storekeeper.elements.Position;
import com.qubo.challenge.storekeeper.elements.StoreKeeper;
import com.qubo.challenge.storekeeper.elements.Terrain;

/**
 * �w�肳�ꂽ�t�@�C��������A{@link StoreKeeper}�I�u�W�F�N�g�𐶐�����N���X
 * @author Qubo
 */
public class Loader {
	/**
	 * �w�肳�ꂽ�t�@�C��������A{@link StoreKeeper}�I�u�W�F�N�g�𐶐�����
	 * @param path �t�@�C����
	 * @return {@link StoreKeeper}�I�u�W�F�N�g
	 * @throws FileNotFoundException �t�@�C�������݂��Ȃ������ꍇ�ɔ���
	 */
	public StoreKeeper load(String path) throws FileNotFoundException {
		List<List<Cell>> map = new ArrayList<List<Cell>>();
		int maxLength = 0;
		int row = 0;

		for (String line : Utils.forEachLine(path)) {
			if (shouldSkip(line))
				continue;
			List<Cell> cellRow = new ArrayList<Cell>();
			for (int col = 0; col < line.length(); col++) {
				char c = line.charAt(col);
				Terrain field = toTerrain(c);
				FieldObject object = toObject(c);
				Position position = new Position(row, col);
				cellRow.add(new Cell(field, object, position));
			}
			maxLength = Math.max(maxLength, cellRow.size());
			map.add(cellRow);
			row++;
		}

		return new StoreKeeper(toCells(map, maxLength));
	}

	/**
	 * ���͍s�����������X�L�b�v���邩�ǂ������擾
	 * @param line ���͍s
	 * @return
	 */
	private boolean shouldSkip(String line) {
		return line.startsWith(Constants.COMMENT_START) || line.length() == 0;
	}
	/**
	 * {@link List}&lt{@link List}&lt;{@link Cell}&gt;&gt;����{@link Cell}�̓񎟌��z��ɕϊ�����
	 * @param matrix {@link List}&lt{@link List}&lt;{@link Cell}&gt;&gt;�I�u�W�F�N�g
	 * @param maxLength {@link Cell}�̓񎟌��z��
	 * @return
	 */
	private Cell[][] toCells(List<List<Cell>> matrix, int maxLength) {
		Cell[][] cells = new Cell[matrix.size()][maxLength];
		for (int row = 0; row < matrix.size(); row++) {
			List<Cell> objectRow = matrix.get(row);
			for (int col = 0; col < objectRow.size(); col++) {
				cells[row][col] = objectRow.get(col);
			}
		}
		return cells;
	}
	/**
	 * �^����ꂽ�������A{@link FieldObject}�ɕϊ�����
	 * @param c ����
	 * @return {@code c}�ɑΉ�����{@link FieldObject}
	 */
	private FieldObject toObject(char c) {
		switch (c) {
		case Constants.SYMBOL_PLAYER:
		case Constants.SYMBOL_PLAYER_ON_DEST:
			return FieldObject.Player;
		case Constants.SYMBOL_STUFF:
		case Constants.SYMBOL_STUFF_ON_DEST:
			return FieldObject.Stuff;
		}
		return null;
	}
	/**
	 *  �^����ꂽ�������A{@link Terrain}�ɕϊ�����
	 * @param c ����
	 * @return {@code c}�ɑΉ�����{@link Terrain}
	 */
	private Terrain toTerrain(char c) {
		switch (c) {
		case Constants.SYMBOL_WALL:
			return Terrain.Wall;
		case Constants.SYMBOL_DEST:
		case Constants.SYMBOL_PLAYER_ON_DEST:
		case Constants.SYMBOL_STUFF_ON_DEST:
			return Terrain.Dest;
		}
		return null;
	}
}
