package pt.MyDictionary.model;

public class WildcardMatch {
	public boolean compare(String str, String r) {

		// if one is null you do not compare
		if (r == null || str == null) {
			return false;
		}

		// "*" true for any string
		if (r.equals("*")) {
			return true;
		}

		// "?" and only one letter, true for any string
		if (r.equals("?") && str.length() == 1) {
			return true;
		}

		// if no wildcards compare r with str
		if (!r.contains("*") && !r.contains("?")) {
			return str.equals(r);
		}

		String token = null;
		if (r.contains("*")) {
			token = "*";
			// String before *, if any...
			String before = r.substring(0, r.indexOf(token));
			// System.out.printf("\n%s %d",before,before.length());
			// String after *, if any...
			String after = r.substring(r.indexOf(token) + 1, r.length());
			// System.out.printf("\n%s %d",after,after.length());

			boolean bmatches = true;
			if (before != null && before.length() != 0) {
				// System.out.printf("\nstr.indexOf(before) ==
				// %d\n",str.indexOf(before));
				if (str.indexOf(before) == 0) {
					bmatches = true;
				} else {
					bmatches = false;
				}
			}
			boolean amatches = true;
			if (after != null && after.length() != 0) {
				// System.out.printf("\nstr.lastIndexOf(after) ==
				// %d\n",str.indexOf(after));
				// System.out.printf("\n(str.length() - after.length()) = %d\n",
				// (str.length() - after.length()));
				if (str.lastIndexOf(after) == (str.length() - after.length())) {
					amatches = true;
				} else {
					amatches = false;
				}
			}
			return bmatches && amatches;
		}

		if (r.contains("?")) {
			token = "?";
			// String before *, if any...
			String before = r.substring(0, r.indexOf(token));
			// System.out.printf("\n%s %d", before, before.length());
			// String after *, if any...
			String after = r.substring(r.indexOf(token) + 1, r.length());
			// System.out.printf("\n%s %d", after, after.length());

			boolean bmatches = false;
			if (before != null && before.length() != 0) {
				if (str.length() >= r.length()) {
					String before1 = str.substring(0, r.indexOf(token));
					// System.out.printf("\nstr.indexOf(before) ==
					// %d\n",str.indexOf(before));
					if (before1 != null && before1.length() == before.length())
						if (before1.equals(before)) {
							bmatches = true;
						} else {
							bmatches = false;
						}
				}
			}
			boolean amatches = false;
			if (after != null && after.length() != 0) {
				// System.out.printf("\nstr.lastIndexOf(after) ==
				// %d\n",str.indexOf(after));
				// System.out.printf("\n(str.length() - after.length()) = %d\n",
				// (str.length() - after.length()));
				if (str.length() >= r.length()) {
					String after1 = str.substring(r.indexOf(token) + 1, str.length());
					if (after1 != null && after1.length() == after.length())
						if (after.equals(after1)) {
							amatches = true;
						} else {
							amatches = false;
						}
				}
			}
			return bmatches && amatches;
		}

		return false;
	}
}
