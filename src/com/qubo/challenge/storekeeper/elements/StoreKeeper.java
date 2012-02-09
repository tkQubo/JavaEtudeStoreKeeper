package com.qubo.challenge.storekeeper.elements;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * �q�ɔԃQ�[����\�������N���X
 * @author Qubo
 */
public class StoreKeeper implements Iterable<Iterable<Cell>> {
	private final Cell[][] cells;
	private final Stack<Move> history;

	/**
	 * �W���̃R���X�g���N�^
	 * @param cells �񎟌��z��̃Z�����
	 */
	public StoreKeeper(Cell[][] cells) {
		this.cells = cells;
		this.history = new Stack<Move>();
	}

	/**
	 * �Q�[�����N���A���Ă��邩�ǂ������擾�B
	 * ����́A�u�S�Ắw�ו��x���w�ו��u����Z���x��ɏ���Ă��邩�ǂ����v�Ŕ��f�����B
	 * @return �Q�[�����N���A���Ă��邩�ǂ���
	 */
	public boolean isCleared() {
		for (Cell[] row : cells) {
			for (Cell cell : row) {
				if (cell.getTerrain() == Terrain.Dest && cell.getObject() != FieldObject.Stuff)
					return false;
			}
		}
		return true;
	}
	/**
	 * �Q�[�������Z�b�g���A�S�Ă̔z�u��������Ԃɖ߂�
	 */
	public void reset() {
		history.clear();
		for (Cell[] row : cells) {
			for (Cell cell : row) {
				cell.reset();
			}
		}
	}
	/**
	 * �v���[���[��^����ꂽ�����ɐi�߂�
	 * @param direction ����
	 * @return
	 * �ړ��������������ǂ�����Ԃ��B
	 * �Ⴆ�ΕǂɈړ����悤�Ƃ����ꍇ�A���邢�͉ו����ז������Ă���ꍇ��{@code false}��Ԃ��B
	 */
	public boolean move(Direction direction) {
		Move move = getMove(direction.toPosition());
		if (move != null) {
			history.push(move);
			doMove(move, false);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * ���O�̈ړ���������
	 * @return
	 * �����������ǂ�����Ԃ��B
	 * �Ⴆ�΁A����ȏ�������ł��Ȃ��ꍇ�i������ԂȂǁj��{@code false}��Ԃ��B
	 */
	public boolean undo() {
		if (!history.isEmpty()) {
			Move move = history.pop();
			doMove(move, true);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * �Q�[���}�b�v��{@code position}��̃Z�����擾����
	 * @param position
	 * @return �Q�[���}�b�v��{@code position}��̃Z��
	 */
	public Cell getCell(Position position) {
		return cells[position.getRow()][position.getCol()];
	}
	/**
	 * ���݂̃X�e�b�v�����擾����
	 * @return ���݂̃X�e�b�v��
	 */
	public int getStep() { return history.size(); }

	/**
	 * �����p�֐��B�v���[���[��^����ꂽ�����ɐi�߂�B
	 * @param move
	 * @param undoing ����������ɂ��ړ����ʏ푀��ɂ��ړ�����Ԃ�
	 */
	private void doMove(Move move, boolean undoing) {
		Position posPlayer = getPlayerPosition();
		if (move.shouldPushStuff()) {
			Position posNext = posPlayer.add(move.getDelta());
			Position posPrev = posPlayer.add(move.getDelta().reverse());

			Position pivot = !undoing ? posNext : posPlayer;
			Position first = !undoing ? posNext.add(move.getDelta()) : posPrev;
			Position second = !undoing ? posPlayer : posNext;
			swap(pivot, first);
			swap(pivot, second);
		} else {
			Position posDestination = posPlayer.add(!undoing ? move.getDelta() : move.getDelta().reverse());
			swap(posPlayer, posDestination);
		}
	}
	/**
	 * ��_�̃Z����̃I�u�W�F�N�g����������B
	 * @param pos1
	 * @param pos2
	 */
	private void swap(Position pos1, Position pos2) {
		Cell cell1 = getCell(pos1);
		Cell cell2 = getCell(pos2);
		FieldObject temp = cell1.getObject();
		cell1.setObject(cell2.getObject());
		cell2.setObject(temp);
	}
	/**
	 * �v���[���[��{@code delta}�����ړ����邽�߂�{@link Move}�I�u�W�F�N�g��Ԃ��B
	 * �ړ��ł��Ȃ��ꍇ��{@code null}��Ԃ��B
	 * @param delta �ړ�����
	 * @return �v���[���[��{@code delta}�����ړ����邽�߂�{@link Move}�I�u�W�F�N�g
	 */
	private Move getMove(Position delta) {
		Position player = getPlayerPosition();
		Position destination = player.add(delta);
		Cell cell = getCell(destination);
		Terrain field = cell.getTerrain();
		FieldObject object = cell.getObject();

		if (field != Terrain.Wall) {
			if (object == null) {
				return new Move(delta, false);
			} else if (object == FieldObject.Stuff) {
				destination = destination.add(delta);
				field = getCell(destination).getTerrain();
				if (field != Terrain.Wall) {
					return new Move(delta, true);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	/**
	 * ���݂̃v���[���[�̈ʒu��Ԃ�
	 * @return ���݂̃v���[���[�̈ʒu
	 */
	private Position getPlayerPosition() {
		for (Cell[] row : cells) {
			for (Cell cell : row) {
				if (cell.getObject() == FieldObject.Player) {
					return cell.getPosition();
				}
			}
		}
		return null;
	}

	/*
	 * (�� Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Cell[] row : cells) {
			for (Cell cell : row) {
				char ch = cell.toChar();
				builder.append(ch);
			}
			builder.append(Constants.LINE_SEPARATOR);
		}
		return builder.toString();
	}
	/*
	 * (�� Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Iterable<Cell>> iterator() {
		final Iterator<Cell[]> rowIterator = Arrays.asList(cells).iterator();
		return new Iterator<Iterable<Cell>>() {
			@Override
			public Iterable<Cell> next() {
				if (!rowIterator.hasNext()) throw new NoSuchElementException();
				final Iterator<Cell> cellIterator = Arrays.asList(rowIterator.next()).iterator();
				return new Iterable<Cell>() {
					@Override
					public Iterator<Cell> iterator() {
						return new Iterator<Cell>() {
							@Override
							public Cell next() {
								if (!cellIterator.hasNext()) throw new NoSuchElementException();
								return cellIterator.next();
							}
							@Override public boolean hasNext() { return cellIterator.hasNext(); }
							@Override public void remove() { throw new UnsupportedOperationException(); }
						};
					}
				};
			}
			@Override public boolean hasNext() { return rowIterator.hasNext(); }
			@Override public void remove() { throw new UnsupportedOperationException(); }
		};
	}
}
