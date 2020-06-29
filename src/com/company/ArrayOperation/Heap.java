package com.company.ArrayOperation;

public class Heap {

    //215. 数组中的第K个最大元素
    public int findKthLargest(int[] nums, int k) {
        for(int i=nums.length/2-1;i>=0;i--){
            adjustHeap(nums,i,nums.length);
        }
        int lenIndex=nums.length-k;
        for(int j=nums.length-1;j>=lenIndex;j--){
            int temp=nums[j];
            nums[j]=nums[0];
            nums[0]=temp;
            adjustHeap(nums,0,j);
        }
        return nums[lenIndex];
    }

    public void adjustHeap(int[] nums,int i,int length){
        int temp=nums[i];
        for(int k=2*i+1;k<length;k=2*k+1){
            if(k+1<length && nums[k+1]>nums[k]){
                k++;
            }
            if(nums[k]>temp){
                nums[i]=nums[k];
                i=k;
            }else{
                break;
            }
        }
        nums[i]=temp;
    }

}
