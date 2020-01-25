class NodeP{
	public String key;
	public Participant value;
	public NodeP leftChild;
	public NodeP rightChild;
	public NodeP parent;
	public int height;

	public NodeP(String k, Participant v){
		key = k;
		value = v;
		leftChild = null;
		rightChild = null;
		parent =null;
		height = 1;	//counting the extra leaf nodes as in class
	}
}

public class AVLTreeP{
	public int size;
	public NodeP root;
	public AVLTreeP(){
		size = 0;
		root = null;
	}

	public int getHeight(NodeP N){
		if(N != null){
			return(N.height);
		}else{
			return(0);
		}
	}

	public NodeP TrinodeRestructuring(NodeP x, NodeP y, NodeP z){
		NodeP dummy = z.parent;

		//Case-1 right right case
		if (y==z.rightChild && x==y.rightChild){
			if (dummy == null){
				NodeP T2 = y.leftChild;
				z.rightChild = T2;
				if(T2 != null){T2.parent = z;}
				z.parent = y;
				y.leftChild = z;
				y.parent = null;
				root = y;
				//recomputing heights
				z.height = 1 + Math.max(getHeight(z.leftChild), getHeight(z.rightChild));
				y.height = 1 + Math.max(getHeight(x), getHeight(z));
			}else{
				if(z == dummy.leftChild){
					NodeP T2 = y.leftChild;
					z.rightChild = T2;
					if(T2 != null){T2.parent = z;}
					z.parent = y;
					y.leftChild = z;
					y.parent = dummy;
					dummy.leftChild = y;
				}else{
					NodeP T2 = y.leftChild;
					z.rightChild = T2;
					if(T2 != null){T2.parent = z;}
					z.parent = y;
					y.leftChild = z;
					y.parent = dummy;
					dummy.rightChild = y;
				}
				//recomputing heights
				z.height = 1 + Math.max(getHeight(z.leftChild), getHeight(z.rightChild));
				y.height = 1 + Math.max(getHeight(x), getHeight(z));
				
				while (dummy!=null){
					int oldh = dummy.height;
					dummy.height = 1 + Math.max(getHeight(dummy.leftChild), getHeight(dummy.rightChild));
					if(dummy.height == oldh){break;}
					dummy = dummy.parent; 
				}
			}
			return(y);
		}

		//Case-2 left left case
		else if(y==z.leftChild && x==y.leftChild){
			if (dummy == null){
				NodeP T3 = y.rightChild;
				if (T3!=null){T3.parent = z;}
				z.leftChild = T3;
				z.parent = y;
				y.rightChild = z;
				y.parent = null;
				root = y;
				//recomputing heights
				z.height = 1 + Math.max(getHeight(z.leftChild), getHeight(z.rightChild));
				y.height = 1 + Math.max(getHeight(x), getHeight(z));
			}else{
				if(z == dummy.leftChild){
					NodeP T3 = y.rightChild;
					if (T3!=null){T3.parent = z;}
					z.leftChild = T3;
					z.parent = y;
					y.rightChild = z;
					y.parent = dummy;
					dummy.leftChild = y;
				}else{
					NodeP T3 = y.rightChild;
					if (T3!=null){T3.parent = z;}
					z.leftChild = T3;
					z.parent = y;
					y.rightChild = z;
					y.parent = dummy;
					dummy.rightChild = y;
				}
				//recomputing heights
				z.height = 1 + Math.max(getHeight(z.leftChild), getHeight(z.rightChild));
				y.height = 1 + Math.max(getHeight(x), getHeight(z));
				
				while (dummy!=null){
					int oldh = dummy.height;
					dummy.height = 1 + Math.max(getHeight(dummy.leftChild), getHeight(dummy.rightChild));
					if(dummy.height == oldh){break;}
					dummy = dummy.parent;	 
				}
			}
			return(y);
		}
		
		//Case-3 Right Left Case
		else if (y==z.rightChild && x==y.leftChild){
			if (dummy==null){
				NodeP T2 = x.leftChild;
				NodeP T3 = x.rightChild;
				if (T2!=null){T2.parent = z;}
				z.rightChild = T2;
				if (T3!=null){T3.parent = y;}
				y.leftChild = T3;
				z.parent = x;
				y.parent = x;
				x.leftChild = z;
				x.rightChild = y;
				x.parent=null;
				root=x;
				//recomputing heights
				z.height = 1 + Math.max(getHeight(z.leftChild), getHeight(z.rightChild));
				y.height = 1 + Math.max(getHeight(y.leftChild), getHeight(y.rightChild));
				x.height = 1 + Math.max(getHeight(y) , getHeight(z));
			}else{
				if (z==dummy.leftChild){
					NodeP T2 = x.leftChild;
					NodeP T3 = x.rightChild;
					if (T2!=null){T2.parent = z;}
					z.rightChild = T2;
					if (T3!=null){T3.parent = y;}
					y.leftChild = T3;
					z.parent = x;
					y.parent = x;
					x.leftChild = z;
					x.rightChild = y;
					x.parent = dummy;
					dummy.leftChild = x;
				}else{
					NodeP T2 = x.leftChild;
					NodeP T3 = x.rightChild;
					if (T2!=null){T2.parent = z;}
					z.rightChild = T2;
					if (T3!=null){T3.parent = y;}
					y.leftChild = T3;
					z.parent = x;
					y.parent = x;
					x.leftChild = z;
					x.rightChild = y;
					x.parent = dummy;
					dummy.rightChild = x;
				}

				//recomputing heights
				z.height = 1 + Math.max(getHeight(z.leftChild), getHeight(z.rightChild));
				y.height = 1 + Math.max(getHeight(y.leftChild), getHeight(y.rightChild));
				x.height = 1 + Math.max(getHeight(y) , getHeight(z));

				while (dummy!=null){
					int oldh = dummy.height;
					dummy.height = 1 + Math.max(getHeight(dummy.leftChild), getHeight(dummy.rightChild));
					if(dummy.height == oldh){break;} 
					dummy = dummy.parent;
				}
			}
			return(x);
		}	

		//Case-4 left right case
		else{
			if (dummy==null){
				NodeP T2 = x.leftChild;
				NodeP T3 = x.rightChild;
				if(T2!=null){T2.parent = y;}
				y.rightChild = T2;
				if(T3!=null){T3.parent = z;}
				z.leftChild = T3;
				z.parent = x;
				y.parent = x;
				x.leftChild = y;
				x.rightChild = z;
				x.parent=null;
				root=x;
				//recomputing heights
				z.height = 1 + Math.max(getHeight(z.leftChild), getHeight(z.rightChild));
				y.height = 1 + Math.max(getHeight(y.leftChild), getHeight(y.rightChild));
				x.height = 1 + Math.max(getHeight(y) , getHeight(z));
			}else{
				if (z==dummy.leftChild){
					NodeP T2 = x.leftChild;
					NodeP T3 = x.rightChild;
					if(T2!=null){T2.parent = y;}
					y.rightChild = T2;
					if(T3!=null){T3.parent = z;}
					z.leftChild = T3;
					z.parent = x;
					y.parent = x;
					x.leftChild = y;
					x.rightChild = z;
					x.parent = dummy;
					dummy.leftChild = x;
				}else{
					NodeP T2 = x.leftChild;
					NodeP T3 = x.rightChild;
					if(T2!=null){T2.parent = y;}
					y.rightChild = T2;
					if(T3!=null){T3.parent = z;}
					z.leftChild = T3;
					z.parent = x;
					y.parent = x;
					x.leftChild = y;
					x.rightChild = z;
					x.parent = dummy;
					dummy.rightChild = x;
				}

				//recomputing heights
				z.height = 1 + Math.max(getHeight(z.leftChild), getHeight(z.rightChild));
				y.height = 1 + Math.max(getHeight(y.leftChild), getHeight(y.rightChild));
				x.height = 1 + Math.max(getHeight(y) , getHeight(z));

				while (dummy!=null){
					int oldh = dummy.height;
					dummy.height = 1 + Math.max(getHeight(dummy.leftChild), getHeight(dummy.rightChild));
					if(dummy.height == oldh){break;}
					dummy = dummy.parent; 
				}
			}
			return(x);
		}
	}

	public void BalanceAfterPut(NodeP p){
		NodeP current = p.parent;
		int heightBalance = Math.abs(getHeight(current.leftChild) - getHeight(current.rightChild));
		while (heightBalance <= 1){
			current = current.parent;
			if(current == null){
				return;
			}
			heightBalance = Math.abs(getHeight(current.leftChild) - getHeight(current.rightChild));
		}

		NodeP z = current;
		NodeP y = z.leftChild;
		if(getHeight(z.leftChild) < getHeight(z.rightChild)){
			y = z.rightChild;
		}

		NodeP x = y.leftChild;
		if(getHeight(y.leftChild) < getHeight(y.rightChild)){
			x = y.rightChild;
		}
		TrinodeRestructuring(x,y,z);
	}

	public void BalanceAfterRemove(NodeP p){
		NodeP current = p;
		int heightBalance = Math.abs(getHeight(current.leftChild) - getHeight(current.rightChild));
		while (heightBalance <= 1){
			current = current.parent;
			if(current == null){
				return;
			}
			heightBalance = Math.abs(getHeight(current.leftChild) - getHeight(current.rightChild));
		}

		NodeP z = current;
		NodeP y = z.leftChild;
		if(getHeight(z.leftChild) < getHeight(z.rightChild)){
			y = z.rightChild;
		}
		NodeP x1 = y.leftChild;
		NodeP x2 = y.rightChild;
		NodeP x;
		if(getHeight(x1) < getHeight(x2)){
			x = x2;
		}else if (getHeight(x1) > getHeight(x2)){
			x = x1;
		}else{
			if(y==z.leftChild){
				x = y.leftChild;
			}else{
				x = y.rightChild;
			}
		}
		NodeP b = TrinodeRestructuring(x,y,z);
		if(b != root){
			BalanceAfterRemove(b.parent);
		}
	}

	//this method inserts a Node(k,v) in the tree; throws an exception if key already exists
	public void put(String k, Participant v) throws Exception{
		NodeP newNode = new NodeP(k,v);
		if (root == null){
			root = newNode;
			size++;
		}
		else{
			NodeP current = root;
			NodeP currentParent = null;
			while(current != null){
				currentParent = current;
				if (k.compareTo(current.key) < 0){
					current = current.leftChild;
				}
				else{
					if (k.compareTo(current.key) > 0){
						current = current.rightChild;
					}
					else{
						//System.out.println("Error : " + k + " key already exists");
						throw new Exception();
					}
				}
			}

			if (k.compareTo(currentParent.key) < 0){
				currentParent.leftChild = newNode;
				newNode.parent = currentParent;
				size++;
				//updating heights
				while(currentParent!=null){
					int oldh = currentParent.height;
					currentParent.height = 1 + Math.max(getHeight(currentParent.leftChild), getHeight(currentParent.rightChild));
					if (currentParent.height == oldh){break;}
					currentParent = currentParent.parent;
				}
				BalanceAfterPut(newNode);
			}
			else{
				if (k.compareTo(currentParent.key) > 0){
					currentParent.rightChild = newNode;
					newNode.parent= currentParent;
					size++;
					//updating heights
					while(currentParent!=null){
						int oldh = currentParent.height;
						currentParent.height = 1 + Math.max(getHeight(currentParent.leftChild), getHeight(currentParent.rightChild));
						if (currentParent.height == oldh){break;}
						currentParent = currentParent.parent;
					}
					BalanceAfterPut(newNode);
				}
				else{
					//System.out.println("Error : Key already exists");
					throw new Exception();
				}
			}
			
		}
	}

	//this method deletes a Node with key k, if it exists
	public void remove(String k){
		NodeP current = root;	//node to be deleted
		NodeP currentParent = root;
		boolean isLeftChild = false;	//tells whether the node to be deleted is a left child or a right child
		while((current.key).compareTo(k) != 0){
			currentParent = current;
			if (k.compareTo(current.key) < 0){
				isLeftChild = true;
				current = current.leftChild;
			}
			else{
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null){
				return;
			}
		}

		//Case-1 Node to be deleted has no children
		if (current.leftChild==null && current.rightChild==null){
			if (current==root){
				root = null;
				root.height = 0;
			}
			if (isLeftChild==true){
				currentParent.leftChild = null;
				//updating heights
				while(currentParent!=null){
					int oldh = currentParent.height;
					currentParent.height = 1 + Math.max(getHeight(currentParent.leftChild), getHeight(currentParent.rightChild));
					if (currentParent.height == oldh){break;}
					currentParent = currentParent.parent;
				}
				BalanceAfterRemove(current.parent);
			}
			else{
				currentParent.rightChild = null;
				//updating heights
				while(currentParent!=null){
					int oldh = currentParent.height;
					currentParent.height = 1 + Math.max(getHeight(currentParent.leftChild), getHeight(currentParent.rightChild));
					if (currentParent.height == oldh){break;}
					currentParent = currentParent.parent;
				}
				BalanceAfterRemove(current.parent);
			}
		}

		//Case-2 Node to be deleted has only one child
		else if (current.rightChild == null){
			if (current==root){
				root = current.leftChild;
				root.parent = null;
			}
			else if(isLeftChild == true){
				currentParent.leftChild = current.leftChild;
				currentParent.leftChild.parent = currentParent;
				//updating heights
				while(currentParent!=null){
					int oldh = currentParent.height;
					currentParent.height = 1 + Math.max(getHeight(currentParent.leftChild), getHeight(currentParent.rightChild));
					if (currentParent.height == oldh){break;}
					currentParent = currentParent.parent;
				}
				BalanceAfterRemove(current.parent);
			}
			else{
				currentParent.rightChild = current.leftChild;
				currentParent.rightChild.parent = currentParent; 
				//updating heights
				while(currentParent!=null){
					int oldh = currentParent.height;
					currentParent.height = 1 + Math.max(getHeight(currentParent.leftChild), getHeight(currentParent.rightChild));
					if (currentParent.height == oldh){break;}
					currentParent = currentParent.parent;
				}
				BalanceAfterRemove(current.parent);
			}
		}
		else if (current.leftChild == null){
			if (current==root){
				root = current.rightChild;
				root.parent = null;
			}
			else if(isLeftChild == true){
				currentParent.leftChild = current.rightChild;
				currentParent.leftChild.parent = currentParent;
				//updating heights
				while(currentParent!=null){
					int oldh = currentParent.height;
					currentParent.height = 1 + Math.max(getHeight(currentParent.leftChild), getHeight(currentParent.rightChild));
					if (currentParent.height == oldh){break;}
					currentParent = currentParent.parent;
				}
				BalanceAfterRemove(current.parent);
			}
			else{
				currentParent.rightChild = current.rightChild;
				currentParent.rightChild.parent = currentParent;
				//updating heights
				while(currentParent!=null){
					int oldh = currentParent.height;
					currentParent.height = 1 + Math.max(getHeight(currentParent.leftChild), getHeight(currentParent.rightChild));
					if (currentParent.height == oldh){break;}
					currentParent = currentParent.parent;
				}
				BalanceAfterRemove(current.parent);
			}
		}

		//Case-3 Node to be deleted has two children
		else if (current.leftChild!=null && current.rightChild!=null){
			NodeP sub = null;	//this will come in place of deleted Node
			//finding maximum element in left sub-tree
			NodeP subChild = current.leftChild;
			NodeP subParent = null;
			while(subChild != null){
				subParent = sub;
				sub = subChild;
				subChild = subChild.rightChild;
			}
			//if sub has a leftChild, then make it the right child of subParent
			if (sub != current.leftChild){
				subParent.rightChild = sub.leftChild;
				if(subParent.rightChild!= null){subParent.rightChild.parent = subParent;}
				sub.leftChild = current.leftChild;
				sub.leftChild.parent = sub;
				sub.parent = null;
				//updating heights
				while(subParent!=null){
					int oldh = subParent.height;
					if(subParent == sub){
						subParent.height = 1 + Math.max(getHeight(subParent.leftChild), getHeight(current.rightChild));
					}else{
						subParent.height = 1 + Math.max(getHeight(subParent.leftChild), getHeight(subParent.rightChild));
					}
					//if (currentParent.height == oldh){break;}
					subParent = subParent.parent;
				}
			}else{
				sub.height = current.height;
			}

			if(current == root){
				root = sub;
				sub.parent = null;
			}
			else if(isLeftChild == true){
				currentParent.leftChild = sub;
				currentParent.leftChild.parent = currentParent;
			}
			else{
				currentParent.rightChild = sub;
				currentParent.rightChild.parent = currentParent;
			}

			sub.rightChild = current.rightChild;
			sub.rightChild.parent = sub;

			//updating heights
			while(currentParent!=null){
				int oldh = currentParent.height;
				currentParent.height = 1 + Math.max(getHeight(currentParent.leftChild), getHeight(currentParent.rightChild));
				if (currentParent.height == oldh){break;}
				currentParent = currentParent.parent;
			}
			if (current.parent!=null){
				BalanceAfterRemove(current.parent);}
		}

		size--;
	}

	//this method returns true if a node with key k already exists
	public boolean search(String k){
		NodeP current = root;
		while (current!=null){
			if (k.compareTo(current.key) == 0){
				return true;
			}else if (k.compareTo(current.key) < 0){
				current = current.leftChild;
			}else{
				current = current.rightChild;
			}
		}
		return false;
	}

	//this method returns the value of node with key k, throws exception if key k doesn't exist
	public Participant get(String k) throws Exception{
		NodeP current = root;
		while (current!=null){
			if (k.compareTo(current.key) == 0){
				return (current.value);
			}else if (k.compareTo(current.key) < 0){
				current = current.leftChild;
			}else{
				current = current.rightChild;
			}
		}
		//System.out.println("key doesn't exist");
		throw new Exception();
	}

	//this method changes the value corresponding to key k, throws exception if k not there
	public void change(String k,Participant v) throws Exception{
		NodeP current = root;
		while (current!=null){
			if (k.compareTo(current.key) == 0){
				current.value = v;
				return;
			}else if (k.compareTo(current.key) < 0){
				current = current.leftChild;
			}else{
				current = current.rightChild;
			}
		}
		//System.out.println("key doesn't exist");
		throw new Exception();
	}

	/*public void preOrder(NodeP N){
        if (N != null) {
            System.out.print(N.key + " ");
            preOrder(N.leftChild);
            preOrder(N.rightChild);
        }
	}*/

	public void preOrder(NodeP N){
        if (N != null) {
            System.out.print(N.key + "(" + N.value.score + ")" + " ");
            preOrder(N.leftChild);
            preOrder(N.rightChild);
        }
	}

	public void postOrder(NodeP N){
		if (N != null){
			postOrder(N.leftChild);
            postOrder(N.rightChild);
            System.out.print(N.key + " ");
		}
	}
}