package test.com.qubo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import com.qubo.Utils;

public class UtilsTest {
	private static final String GAMEMAP1 = "gamemap_1.txt";
	private static final String GAMEMAP_NONEXISTENT = "gamemap_XXXXXXXXXXXXX.txt";
	private String[] GAME1 = {
			";��1�X�e�[�W",
			"",
			"#########",
			"#       #",
			"# ##o## #",
			"# p   o #",
			"##o...# #",
			"##    # #",
			"####  ###",
			"#########"
	};

	@Test
	public void testForEachLine1() throws FileNotFoundException {
		List<String> lines = new ArrayList<String>();
		for (String line : Utils.forEachLine(GAMEMAP1)) {
			lines.add(line);
		}
		assertThat(lines.size(), is(GAME1.length));
		for (int i = 0; i < lines.size(); i++) {
			assertThat(lines.get(i), is(GAME1[i]));
		}
	}
	@Test
	public void testForEachLine2() {
		Iterable<String> forEachLine = null;
		try {
			forEachLine = Utils.forEachLine(GAMEMAP_NONEXISTENT);
			fail("��O���������Ă��܂���I");
		} catch (FileNotFoundException e) {
		}
		assertNull(forEachLine);
	}
}
