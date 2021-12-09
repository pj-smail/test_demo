package com.jsmail.com.tree;

public class Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
//		List<String> list = new CopyOnWriteArrayList<>();//写时复制
//		Collections.synchronizedList(new ArrayList<>());
//		for (int i = 0; i < 30; i++) {
//			new Thread(() -> {
//				list.add(UUID.randomUUID().toString().substring(0, 8));
//				System.out.println(list);
//			}).start();
//		}
//		Set set = new HashSet();
//		set.add(456);
//		set.add(123);
//		set.add(123);
//		set.add("AA");
//		set.add("CC");
//		set.add(new User("Tom", 21));
//		set.add(new User("Tom", 21));
//		set.add(129);
//		Iterator iterator = set.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
//		Map<Integer, String> map = new HashMap<>();
//		map.put(1, "2");
//		for (int i = 17;i < 200;i+=16) {
//			map.put(i, "2");
//		}
//		System.out.println(map.size());
	}

	static class User{
		private String name;

		private Integer age;

		public User() {
		}

		public User(String name, Integer age) {
			this.name = name;
			this.age = age;
		}

		@Override
		public String toString() {
			return "User{" +
					"name='" + name + '\'' +
					", age='" + age + '\'' +
					'}';
		}

		@Override
		public boolean equals(Object o) {
			System.out.println("User equals.....");
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			User user = (User) o;

			if(age != user.age) {
				return false;
			}
			return name != null ? name.equals(user.name) : user.name == null;
		}

		@Override
		public int hashCode() {
			int result = name != null ? name.hashCode() : 0;
			result = 31 * result + age;
			return result;
		}
	}
}
