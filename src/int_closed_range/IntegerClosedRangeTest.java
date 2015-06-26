package int_closed_range;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class IntegerClosedRangeTest {
	public static class 下端点を返す {
		@Test
		public void 閉区間3から8の下端点3を返す() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(3, 8);
			int actual = integerClosedRange.getLowerEndpoint();
			assertThat(actual, is(3));
		}

		@Test
		public void 閉区間2から5の下端点2を返す() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(2, 5);
			int actual = integerClosedRange.getLowerEndpoint();
			assertThat(actual, is(2));
		}

		@Test
		public void 閉区間負数2から正数7の下端点負数2を返す() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(-2,
					7);
			int actual = integerClosedRange.getLowerEndpoint();
			assertThat(actual, is(-2));
		}
	}

	public static class 上端点を返す {
		@Test
		public void 閉区間3から8の上端点8を返す() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(3, 8);
			int actual = integerClosedRange.getUpperEndpoint();
			assertThat(actual, is(8));
		}

		@Test
		public void 閉区間2から5の上端点5を返す() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(2, 5);
			int actual = integerClosedRange.getUpperEndpoint();
			assertThat(actual, is(5));
		}
	}

	public static class 文字列を返す {
		@Test
		public void 閉区間3から8の文字列を返す() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(3, 8);
			String actual = integerClosedRange.toString();
			assertThat(actual, is("[3,8]"));
		}

		@Test
		public void 閉区間2から5の文字列を返す() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(2, 5);
			String actual = integerClosedRange.toString();
			assertThat(actual, is("[2,5]"));
		}
	}

	public static class 閉区間を生成する {
		@Test(expected = IllegalArgumentException.class)
		public void 上端点より下端点が大きいとき例外を発生する() throws Exception {
			new IntegerClosedRange(8, 3);
		}

		@Test
		public void 閉区間3から3を生成して例外が発生しない_境界値() throws Exception {
			IntegerClosedRange actual = new IntegerClosedRange(3, 3);
			assertThat(actual, is(not(nullValue())));
		}

		@Test(expected = IllegalArgumentException.class)
		public void 閉区間4から3を生成して例外を発生する_境界値() throws Exception {
			new IntegerClosedRange(4, 3);
		}
	}

	public static class 閉区間に含まれる数か判定する {
		@Test
		public void 閉区間3から8は5を含む() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(3, 8);
			boolean actual = integerClosedRange.contains(5);
			assertThat(actual, is(true));
		}

		@Test
		public void 閉区間3から8は2を含まない() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(3, 8);
			boolean actual = integerClosedRange.contains(2);
			assertThat(actual, is(false));
		}

		@Test
		public void 閉区間3から8は9を含まない() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(3, 8);
			boolean actual = integerClosedRange.contains(9);
			assertThat(actual, is(false));
		}

		@Test
		public void 閉区間3から8は8を含む() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(3, 8);
			boolean actual = integerClosedRange.contains(8);
			assertThat(actual, is(true));
		}

		@Test
		public void 閉区間負数3から8は負数3を含む() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(-3,
					8);
			boolean actual = integerClosedRange.contains(-3);
			assertThat(actual, is(true));
		}
	}

	public static class _2つの閉区間が等しいか判定する {
		@Test
		public void 閉区間3から8は閉区間3から8と等しい() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(3, 8);
			boolean actual = integerClosedRange.equals(new IntegerClosedRange(
					3, 8));
			assertThat(actual, is(true));
		}

		@Test
		public void 閉区間3から8は閉区間2から5と等しくない() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(3, 8);
			boolean actual = integerClosedRange.equals(new IntegerClosedRange(
					2, 5));
			assertThat(actual, is(false));
		}

		@Test
		public void 閉区間3から3は閉区間3から3と等しい() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(3, 3);
			boolean actual = integerClosedRange.equals(new IntegerClosedRange(
					3, 3));
			assertThat(actual, is(true));
		}
	}
	
	public static class 閉区間1から10の場合 {
		private IntegerClosedRange range;
		@Before
		public void setup() {
			this.range = new IntegerClosedRange(1, 10);
		}
		@Test
		public void 上端点は10() throws Exception {
			assertThat(this.range.getUpperEndpoint(), is(10));
		}
		@Test
		public void 下端点は1() throws Exception {
			assertThat(this.range.getLowerEndpoint(), is(1));
		}
		@Test
		public void 閉区間11から20を含まない() throws Exception {
			IntegerClosedRange other = new IntegerClosedRange(11, 20);
			assertThat(this.range.contains(other), is(false));
		}		
	}

	public static class 閉区間が閉区間を含むか判定する {
		@Test
		public void 閉区間1から10は閉区間3から8を含む() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(1,
					10);
			boolean actual = integerClosedRange
					.contains(new IntegerClosedRange(3, 8));
			assertThat(actual, is(true));
		}

		@Test
		public void 閉区間1から10は閉区間1から9を含む() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(1,
					10);
			boolean actual = integerClosedRange
					.contains(new IntegerClosedRange(1, 9));
			assertThat(actual, is(true));
		}

		@Test
		public void 閉区間1から10は閉区間2から10を含む() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(1,
					10);
			boolean actual = integerClosedRange
					.contains(new IntegerClosedRange(2, 10));
			assertThat(actual, is(true));
		}

		@Test
		public void 閉区間1から10は閉区間1から10を含む() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(1,
					10);
			boolean actual = integerClosedRange
					.contains(new IntegerClosedRange(1, 10));
			assertThat(actual, is(true));
		}

		@Test
		public void 閉区間1から1は閉区間1から1を含む() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(1, 1);
			boolean actual = integerClosedRange
					.contains(new IntegerClosedRange(1, 1));
			assertThat(actual, is(true));
		}

		@Test
		public void 閉区間1から10は閉区間0から11を含まない() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(1,
					10);
			boolean actual = integerClosedRange
					.contains(new IntegerClosedRange(0, 11));
			assertThat(actual, is(false));
		}

		@Test
		public void 閉区間1から10は閉区間0から10を含まない() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(1,
					10);
			boolean actual = integerClosedRange
					.contains(new IntegerClosedRange(0, 10));
			assertThat(actual, is(false));
		}

		@Test
		public void 閉区間1から10は閉区間1から11を含まない() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(1,
					10);
			boolean actual = integerClosedRange
					.contains(new IntegerClosedRange(1, 11));
			assertThat(actual, is(false));
		}

		@Test
		public void 閉区間1から10は閉区間5から15を含まない() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(1,
					10);
			boolean actual = integerClosedRange
					.contains(new IntegerClosedRange(5, 15));
			assertThat(actual, is(false));
		}

		@Test
		public void 閉区間1から10は閉区間11から20を含まない() throws Exception {
			IntegerClosedRange integerClosedRange = new IntegerClosedRange(1,
					10);
			boolean actual = integerClosedRange
					.contains(new IntegerClosedRange(11, 20));
			assertThat(actual, is(false));
		}
	}
}
