package com.epam.koryagin.concurrent.task.tonnel;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Tonnel {
	
	private BlockingQueue<Train> path = new ArrayBlockingQueue<Train>(2) {
	private static final long serialVersionUID = -3870375802264355874L;

		@Override
		public Train remove() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Train poll() {
			return path.poll();
		}

		@Override
		public Train element() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Train peek() {
			return path.peek();
		}

		@Override
		public int size() {
			return path.size();
		}

		@Override
		public boolean isEmpty() {
			return path.isEmpty();
		}

		@Override
		public Iterator<Train> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> T[] toArray(T[] a) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends Train> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean add(Train e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean offer(Train e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void put(Train e) throws InterruptedException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean offer(Train e, long timeout, TimeUnit unit)
				throws InterruptedException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Train take() throws InterruptedException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Train poll(long timeout, TimeUnit unit)
				throws InterruptedException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int remainingCapacity() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean contains(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int drainTo(Collection<? super Train> c) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int drainTo(Collection<? super Train> c, int maxElements) {
			// TODO Auto-generated method stub
			return 0;
		}
	};

	public BlockingQueue<Train> getPath() {
		return path;
	}

	public void setPath(BlockingQueue<Train> trains) {
		this.path = trains;
	}
}
